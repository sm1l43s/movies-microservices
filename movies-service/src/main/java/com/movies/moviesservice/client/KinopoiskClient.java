package com.movies.moviesservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Kinopoisk", url = "https://kinopoiskapiunofficial.tech/api/v2.2/films")
public interface KinopoiskClient {

    @GetMapping
    String getMovies(@RequestHeader(value = "X-API-KEY") String key,
                     @RequestParam(value = "type", required = false) String type,
                     @RequestParam(value = "ratingFrom", required = false) Integer ratingFrom,
                     @RequestParam(value = "ratingTo", required = false) Integer ratingTo,
                     @RequestParam(value = "yearFrom", required = false) Integer yearFrom,
                     @RequestParam(value = "yearTo", required = false) Integer yearTo,
                     @RequestParam(value = "keyword", required = false) String keyword,
                     @RequestParam(value = "page", required = false) Integer page,
                     @RequestParam(value = "countries", required = false) Integer countries,
                     @RequestParam(value = "genres", required = false) Integer genres,
                     @RequestParam(value = "order", required = false) String order);
    @GetMapping(value = "/{id}")
    String getMovieById(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);
    @GetMapping(value = "/{id}/awards")
    String getAwardsByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);
    @GetMapping(value = "/{id}/videos")
    String getVideosByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);
    @GetMapping(value = "/{id}/similars")
    String getSimilarMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);
    @GetMapping(value = "/{id}/reviews")
    String getReviewsMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);
    @GetMapping(value = "/{id}/images")
    String getImagesMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);
    @GetMapping(value = "/{id}/seasons")
    String getSeasonsByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);
    @GetMapping(value = "/top")
    String getTopMovies(@RequestHeader(value = "X-API-KEY") String key);
    @GetMapping(value = "/premiers")
    String getPremiersMovies(@RequestHeader(value = "X-API-KEY") String key);
    @GetMapping(value = "/search-by-keyword")
    String getMoviesByKeyword(@RequestHeader(value = "X-API-KEY") String key,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "page", required = false) Integer page);
    @GetMapping(value = "/releases")
    String getReleasesMovies(@RequestHeader(value = "X-API-KEY") String key);
}
