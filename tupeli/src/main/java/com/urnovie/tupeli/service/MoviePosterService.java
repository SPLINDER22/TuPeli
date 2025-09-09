package com.urnovie.tupeli.service;

import com.urnovie.tupeli.entity.Movie;

public interface MoviePosterService {
    /**
     * Obtiene los datos de la película desde OMDb por título.
     * Retorna una entidad Movie con todos los campos completados (title, year, director, genre, posterUrl)
     */
    Movie fetchMovieData(String title);
}
