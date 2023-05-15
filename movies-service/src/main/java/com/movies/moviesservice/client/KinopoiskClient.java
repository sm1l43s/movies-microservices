package com.movies.moviesservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Kinopoisk", url = "https://kinopoiskapiunofficial.tech/api/")
public interface KinopoiskClient {

    @GetMapping(value = "v2.2/films")
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

    @GetMapping(value = "v2.2/films/{id}")
    String getMovieById(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);

    @GetMapping(value = "v2.2/films/{id}/awards")
    String getAwardsByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);

    @GetMapping(value = "v2.2/films/{id}/videos")
    String getVideosByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);

    @GetMapping(value = "v2.2/films/{id}/similars")
    String getSimilarMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);

    @GetMapping(value = "v2.2/films/{id}/reviews")
    String getReviewsMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "order", required = false) String order);

    @GetMapping(value = "v2.2/films/{id}/images")
    String getImagesMovieByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "type", required = false) String type);

    @GetMapping(value = "v2.2/films/{id}/seasons")
    String getSeasonsByMovieId(@RequestHeader(value = "X-API-KEY") String key, @PathVariable Integer id);

    @GetMapping(value = "v2.2/films/top")
    String getTopMovies(@RequestHeader(value = "X-API-KEY") String key,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "type", required = false) String type);

    @GetMapping(value = "v2.2/films/premieres")
    String getPremiersMovies(@RequestHeader(value = "X-API-KEY") String key,
                             @RequestParam(value = "year") Integer year,
                             @RequestParam(value = "month") String month);

    @GetMapping(value = "v2.1/films/search-by-keyword")
    String getMoviesByKeyword(@RequestHeader(value = "X-API-KEY") String key,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "page", required = false) Integer page);

    @GetMapping(value = "v2.1/films/releases")
    String getReleasesMovies(@RequestHeader(value = "X-API-KEY") String key,
                             @RequestParam(value = "year") Integer year,
                             @RequestParam(value = "month") String month,
                             @RequestParam(value = "page", required = false) Integer page);
}
