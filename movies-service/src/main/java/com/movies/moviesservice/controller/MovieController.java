package com.movies.moviesservice.controller;

import com.movies.moviesservice.client.AccessKeyClient;
import com.movies.moviesservice.client.KinopoiskClient;
import com.movies.moviesservice.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Movies", description = "Набор методов для работы с данными о фильмах.")
@RestController
@RequestMapping(value = "/api/v1/films", produces = "application/json")
@RequiredArgsConstructor
public class MovieController {

    private final KinopoiskClient kinopoiskClient;
    private final AccessKeyClient accessKeyClient;

    @Operation(summary = "Получить список фильмов по различным фильтрам",
            description = "Возвращает список фильмов с пагинацией. Каждая страница содержит не более чем 20 фильмов. Данный эндпоинт не возращает более 400 фильмов.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Movie.class))
                            )
                    }
            )
    })
    @GetMapping
    public String getMovies(@Parameter(description = "тип фильма. Available values : FILM, TV_SHOW, TV_SERIES, MINI_SERIES, ALL")
                            @RequestParam(value = "type", required = false, defaultValue = "ALL") String type,
                            @Parameter(description = "минимальный рейтинг")
                            @RequestParam(value = "ratingFrom", required = false, defaultValue = "0") Integer ratingFrom,
                            @Parameter(description = "максимальный рейтинг")
                            @RequestParam(value = "ratingTo", required = false, defaultValue = "10") Integer ratingTo,
                            @Parameter(description = "минимальный год")
                            @RequestParam(value = "yearFrom", required = false, defaultValue = "1800") Integer yearFrom,
                            @Parameter(description = "максимальный год")
                            @RequestParam(value = "yearTo", required = false, defaultValue = "3000") Integer yearTo,
                            @Parameter(description = "ключевое слово, которое встречается в названии фильма")
                            @RequestParam(value = "keyword", required = false) String keyword,
                            @Parameter(description = "номер страницы")
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            @Parameter(description = "список id стран разделенные запятой. Например countries=1,2,3. На данный момент можно указать не более одной страны.")
                            @RequestParam(value = "countries", required = false) Integer countries,
                            @Parameter(description = "список id жанров разделенные запятой. Например genres=1,2,3. На данный момент можно указать не более одного жанра.")
                            @RequestParam(value = "genres", required = false) Integer genres,
                            @Parameter(description = "сортировка. Available values : RATING, NUM_VOTE, YEAR")
                            @RequestParam(value = "order", required = false, defaultValue = "RATING") String order) {
        return kinopoiskClient.getMovies(accessKeyClient.getAccessKey(), type, ratingFrom, ratingTo,
                yearFrom, yearTo, keyword, page, countries, genres, order);
    }

    @Operation(summary = "Получить данные о фильме",
            description = "Данный эндпоинт возвращает базовые данные о фильме. Поле lastSync показывает дату последнего обновления данных.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class)
                            )
                    }
            )
    })
    @GetMapping("/{id}")
    public String getMovieById(@Parameter(description = "kinopoisk film id")
                               @PathVariable("id") Integer id) {
        return kinopoiskClient.getMovieById(accessKeyClient.getAccessKey(), id);
    }

    @Operation(summary = "Получить данные о наградах фильма",
            description = "Данный эндпоинт возвращает данные о наградах и премиях фильма.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Award.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/{id}/awards")
    String getAwardsByMovieId(@Parameter(description = "kinopoisk film id")
                              @PathVariable Integer id) {
        return kinopoiskClient.getAwardsByMovieId(accessKeyClient.getAccessKey(), id);
    }

    @Operation(summary = "Получить трейлеры, тизеры, видео для фильма",
            description = "Данный эндпоинт возвращает трейлеры,тизеры,видео для фильма по kinopoisk film id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Video.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/{id}/videos")
    String getVideosByMovieId(@Parameter(description = "kinopoisk film id")
                              @PathVariable Integer id) {
        return kinopoiskClient.getVideosByMovieId(accessKeyClient.getAccessKey(), id);
    }

    @Operation(summary = "Получить список похожих фильмов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Movie.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/{id}/similars")
    String getSimilarMovieByMovieId(@Parameter(description = "kinopoisk film id")
                                    @PathVariable Integer id) {
        return kinopoiskClient.getSimilarMovieByMovieId(accessKeyClient.getAccessKey(), id);
    }

    @Operation(summary = "Получить список рецензий зрителей для фильма",
            description = "Возвращает список рецензии зрителей с пагинацией. Каждая страница содержит не более чем 20 рецензий.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Review.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/{id}/reviews")
    String getReviewsMovieByMovieId(@Parameter(description = "kinopoisk film id")
                                    @PathVariable Integer id,
                                    @Parameter(description = "номер страницы")
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    @Parameter(description = "сортировка. Available values : RATING, NUM_VOTE, YEAR")
                                    @RequestParam(value = "order", required = false, defaultValue = "RATING") String order) {
        return kinopoiskClient.getReviewsMovieByMovieId(accessKeyClient.getAccessKey(), id, page, order);
    }

    @Operation(summary = "Получить изображения(кадры, постеры, фан-арты и т.д.) связанные с данным фльмом",
            description = "Данный эндпоинт возвращает изображения связанные с фильмом с пагинацией. Каждая страница содержит не более чем 20 фильмов.STILL - кадры\n" +
                    "SHOOTING - изображения со съемок POSTER - постеры FAN_ART - фан-арты PROMO - промо CONCEPT - концепт-арты WALLPAPER - обои COVER - обложки SCREENSHOT - скриншоты")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Image.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/{id}/images")
    String getImagesMovieByMovieId(@Parameter(description = "kinopoisk film id")
                                   @PathVariable Integer id,
                                   @Parameter(description = "номер страницы")
                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @Parameter(description = "тип изображения. Available values : STILL, SHOOTING, POSTER, FAN_ART, PROMO, CONCEPT, WALLPAPER, COVER, SCREENSHOT")
                                   @RequestParam(value = "type", required = false, defaultValue = "STILL") String type) {
        return kinopoiskClient.getImagesMovieByMovieId(accessKeyClient.getAccessKey(), id, page, type);
    }

    @Operation(summary = "Получить данные о сезонах для сериала",
            description = "Данный эндпоинт возвращает данные о сезонах для сериала.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Image.class)
                            )
                    }
            )
    })
    @GetMapping(value = "/{id}/seasons")
    String getSeasonsByMovieId(@Parameter(description = "kinopoisk film id")
                               @PathVariable Integer id) {
        return kinopoiskClient.getSeasonsByMovieId(accessKeyClient.getAccessKey(), id);
    }

    @Operation(summary = "Получить список фильмов из различных топов коллекций",
            description = "Возвращает список фильмов с пагинацией. Каждая страница содержит не более чем 20 фильмов.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Movie.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/top")
    String getTopMovies(@Parameter(description = "номер страницы")
                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @Parameter(description = "тип топа или коллекции. Available values : TOP_250_BEST_FILMS, TOP_100_POPULAR_FILMS, TOP_AWAIT_FILMS")
                        @RequestParam(value = "type", required = false, defaultValue = "TOP_250_BEST_FILMS") String type) {
        return kinopoiskClient.getTopMovies(accessKeyClient.getAccessKey(), page, type);
    }

    @Operation(summary = "Получить список кинопремьер",
            description = "Данный эндпоинт возвращает список кинопремьер.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Movie.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/premieres")
    String getPremiersMovies(@Parameter(description = "год релиза")
                             @RequestParam(value = "year") Integer year,
                             @Parameter(description = "месяц релиза. Available values : JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER")
                             @RequestParam(value = "month") String month) {
        return kinopoiskClient.getPremiersMovies(accessKeyClient.getAccessKey(), year, month);
    }

    @Operation(summary = "Получить список фильмов по ключевым словам",
            description = "Возвращает список фильмов с пагинацией. Каждая страница содержит не более чем 20 фильмов.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Movie.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/search-by-keyword")
    String getMoviesByKeyword(@Parameter(description = "ключевое слово, которое встречается в названии фильма")
                              @RequestParam(value = "keyword") String keyword,
                              @Parameter(description = "номер страницы")
                              @RequestParam(value = "page", required = false) Integer page) {
        return kinopoiskClient.getMoviesByKeyword(accessKeyClient.getAccessKey(), keyword, page);
    }

    @Operation(summary = "Получить список цифровых релизов",
            description = "Данный эндпоинт возвращает список цифровых релизов.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Movie.class))
                            )
                    }
            )
    })
    @GetMapping(value = "/releases")
    String getReleasesMovies(@Parameter(description = "год релиза")
                             @RequestParam(value = "year") Integer year,
                             @Parameter(description = "месяц релиза. Available values : JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER")
                             @RequestParam(value = "month") String month,
                             @Parameter(description = "номер страницы")
                             @RequestParam(value = "page", required = false) Integer page) {
        return kinopoiskClient.getReleasesMovies(accessKeyClient.getAccessKey(), year, month, page);
    }
}
