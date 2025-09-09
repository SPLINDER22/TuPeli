package com.urnovie.tupeli.service.impl;

import com.urnovie.tupeli.entity.Movie;
import com.urnovie.tupeli.service.MoviePosterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class MoviePosterServiceImpl implements MoviePosterService {

    private final String URL = "http://www.omdbapi.com/";

    @Value("${omdb.api.key}")
    private String apiKey;

    @Override
    public Movie fetchMovieData(String title) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("t", title)
                .queryParam("apikey", apiKey);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> response = restTemplate.getForObject(builder.toUriString(), Map.class);

        if (response != null && "True".equals(response.get("Response"))) {
            Movie movie = new Movie();
            movie.setTitle(response.get("Title"));
            movie.setEstreno(Long.valueOf(response.get("Year")));
            movie.setDirector(response.get("Director"));
            movie.setGenre(response.get("Genre"));
            movie.setPosterUrl("N/A".equals(response.get("Poster")) ? "/assets/default-poster.jpg" : response.get("Poster"));
            return movie;
        }

        return null; // no se encontró la película
    }
}
