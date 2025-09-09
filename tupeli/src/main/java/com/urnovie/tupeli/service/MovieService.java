package com.urnovie.tupeli.service;

import com.urnovie.tupeli.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();

    List<MovieDTO> getMoviesByUser(Long idUser);

    MovieDTO getMovieById(long id);

    MovieDTO createMovie(MovieDTO movieDTO);

    MovieDTO updateMovie(long id, MovieDTO movieDTO);

    boolean deleteMovie(long id);
}
