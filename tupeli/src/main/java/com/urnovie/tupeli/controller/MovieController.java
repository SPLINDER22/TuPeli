package com.urnovie.tupeli.controller;

import com.urnovie.tupeli.dto.MovieDTO;
import com.urnovie.tupeli.security.UserDetailsImpl;
import com.urnovie.tupeli.service.MovieService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String listMovies(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long idUser = userDetails.getUser().getIdUser();
        model.addAttribute("movies", movieService.getMoviesByUser(idUser));
        model.addAttribute("newMovie", new MovieDTO()); // para el formulario de crear
        return "movies/list";
    }

    @GetMapping("/movies/create")
    public String createMovieView(Model model) {
        MovieDTO movieDTO = new MovieDTO();
        model.addAttribute("movie", movieDTO);
        return "movies/form";
    }

    @PostMapping("/movies/save")
    public String saveMovie(@ModelAttribute("newMovie") MovieDTO movieDTO,
                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        movieDTO.setIdUser(userDetails.getUser().getIdUser());
        movieService.createMovie(movieDTO);
        return "redirect:/movies"; // vuelve a listar
    }

    @PostMapping("/movies/update")
    public String updateMovie(@ModelAttribute MovieDTO movieDTO,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        movieDTO.setIdUser(userDetails.getUser().getIdUser());
        movieService.updateMovie(movieDTO.getIdMovie(), movieDTO);
        return "redirect:/movies";
    }

    @GetMapping("/movies/delete/{movieId}")
    public String deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return "redirect:/movies";
    }
}
