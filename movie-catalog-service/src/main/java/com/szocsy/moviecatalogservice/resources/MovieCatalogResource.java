package com.szocsy.moviecatalogservice.resources;

import com.szocsy.moviecatalogservice.models.CatalogItem;
import com.szocsy.moviecatalogservice.models.Movie;
import com.szocsy.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private WebClient.Builder builder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        return ratings.stream().map(rating -> {
                    //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    Movie movie = builder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId()).retrieve().bodyToMono(Movie.class).block();
                    return new CatalogItem(movie.getName(), "description", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
