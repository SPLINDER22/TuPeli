package com.urnovie.tupeli.impl;

import com.urnovie.tupeli.dto.MovieDTO;
import com.urnovie.tupeli.dto.MovieInfoDTO;
import com.urnovie.tupeli.entity.Movie;
import com.urnovie.tupeli.entity.User;
import com.urnovie.tupeli.repository.MovieRepository;
import com.urnovie.tupeli.repository.UserRepository;
import com.urnovie.tupeli.service.MoviePosterService;
import com.urnovie.tupeli.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MoviePosterService moviePosterService;

    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .toList();
    }

    @Override
    public List<MovieDTO> getMoviesByUser(Long idUser) {
        List<Movie> movies = movieRepository.findByUserIdUser(idUser);
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class)) // posterUrl ya viene de la entidad
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO getMovieById(long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con el id: " + id));
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        // 1️⃣ Obtener el usuario
        User user = userRepository.findById(movieDTO.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2️⃣ Validar que no exista la película para este usuario
        Optional<Movie> existing = movieRepository.findByTitleAndUserIdUser(movieDTO.getTitle(), user.getIdUser());
        if (existing.isPresent()) {
            throw new RuntimeException("La película ya existe para este usuario");
        }

        // 3️⃣ Obtener datos de OMDb
        Movie movieFromApi = moviePosterService.fetchMovieData(movieDTO.getTitle());
        if (movieFromApi == null) {
            throw new RuntimeException("No se pudo obtener información de OMDb para la película");
        }

        // 4️⃣ Asignar usuario
        movieFromApi.setUser(user);

        // 5️⃣ Guardar en base de datos
        Movie saved = movieRepository.save(movieFromApi);

        // 6️⃣ Devolver DTO
        return modelMapper.map(saved, MovieDTO.class);
    }

    @Override
    public MovieDTO updateMovie(long id, MovieDTO movieDTO) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con el id: " + id));
        existingMovie.setEstreno(movieDTO.getEstreno());
        existingMovie.setDirector(movieDTO.getDirector());
        existingMovie.setTitle(movieDTO.getTitle());
        existingMovie.setGenre(movieDTO.getGenre());
        if (movieDTO.getIdUser() != null) {
            User user = userRepository.findById(movieDTO.getIdUser())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + movieDTO.getIdUser()));
            existingMovie.setUser(user);
        }
        existingMovie = movieRepository.save(existingMovie);
        return modelMapper.map(existingMovie, MovieDTO.class);
    }

    @Override
    public boolean deleteMovie(long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con el id: " + id));
        movieRepository.delete(movie);
        return true;
    }
}
