package com.movies.moviesservice.controller;

import com.movies.moviesservice.client.KinopoiskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/films", produces = "application/json")
@RequiredArgsConstructor
public class MovieController {

    private final KinopoiskClient kinopoiskClient;

    String key = "5a1024a3-3b85-42dd-bf55-438755279755";

    @GetMapping
    public String getMovies(@RequestParam(value = "type", required = false) String type,
                            @RequestParam(value = "ratingFrom", required = false) Integer ratingFrom,
                            @RequestParam(value = "ratingTo", required = false) Integer ratingTo,
                            @RequestParam(value = "yearFrom", required = false) Integer yearFrom,
                            @RequestParam(value = "yearTo", required = false) Integer yearTo,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            @RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "countries", required = false) Integer countries,
                            @RequestParam(value = "genres", required = false) Integer genres,
                            @RequestParam(value = "order", required = false) String order) {
        return kinopoiskClient.getMovies(key, type, ratingFrom, ratingTo,
                yearFrom, yearTo, keyword, page, countries, genres, order);
    }
    @GetMapping("/{id}")
    public String getMovieById(@PathVariable("id") Integer id) {
        return kinopoiskClient.getMovieById(key, id);
    }

    @GetMapping(value = "/{id}/awards")
    String getAwardsByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id) {
        return kinopoiskClient.getAwardsByMovieId(key, id);
    }

    @GetMapping(value = "/{id}/videos")
    String getVideosByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id) {
        return kinopoiskClient.getVideosByMovieId(key, id);
    }

    @GetMapping(value = "/{id}/similars")
    String getSimilarMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id) {
        return kinopoiskClient.getSimilarMovieByMovieId(key, id);
    }

    @GetMapping(value = "/{id}/reviews")
    String getReviewsMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id) {
        return kinopoiskClient.getReviewsMovieByMovieId(key, id);
    }

    @GetMapping(value = "/{id}/images")
    String getImagesMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id) {
        return kinopoiskClient.getImagesMovieByMovieId(key, id);
    }
    @GetMapping(value = "/{id}/seasons")
    String getSeasonsByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id) {
        return kinopoiskClient.getSeasonsByMovieId(key, id);
    }
    @GetMapping(value = "/top")
    String getTopMovies(@RequestHeader(value = "X-API-KEY") String key) {
        return kinopoiskClient.getTopMovies(key);
    }
    @GetMapping(value = "/premiers")
    String getPremiersMovies(@RequestHeader(value = "X-API-KEY") String key) {
        return kinopoiskClient.getPremiersMovies(key);
    }
    @GetMapping(value = "/search-by-keyword")
    String getMoviesByKeyword(@RequestHeader(value = "X-API-KEY") String key,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "page", required = false) Integer page) {
        return kinopoiskClient.getMoviesByKeyword(key, keyword, page);
    }
    @GetMapping(value = "/releases")
    String getReleasesMovies(@RequestHeader(value = "X-API-KEY") String key) {
        return kinopoiskClient.getReleasesMovies(key);
    }
}
