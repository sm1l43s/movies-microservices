package com.movies.moviesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Long kinopoiskId;
    private String imdbId;
    private String nameRu;
    private String nameEn;
    private String nameOriginal;
    private String posterUrl;
    private String posterUrlPreview;
    private String coverUrl;
    private String logoUrl;
    private Integer reviewsCount;
    private Integer ratingGoodReview;
    private Integer ratingGoodReviewVoteCount;
    private double ratingKinopoisk;
    private Integer ratingKinopoiskVoteCount;
    private double ratingImdb;
    private Integer ratingImdbVoteCount;
    private Integer ratingFilmCritics;
    private Integer ratingFilmCriticsVoteCount;
    private Integer ratingAwait;
    private Integer ratingAwaitCount;
    private Integer ratingRfCritics;
    private Integer ratingRfCriticsVoteCount;
    private String webUrl;
    private Integer year;
    private Integer filmLength;
    private String slogan;
    private String description;
    private String shortDescription;
    private String editorAnnotation;
    private Boolean isTicketsAvailable;
    private String productionStatus;
    private String[] type;
    private String ratingMpaa;
    private String ratingAgeLimits;
    private Boolean hasImax;
    private Boolean has3D;
    private String lastSync;
    private String[] countries;
    private String[] genres;
    private Integer startYear;
    private Integer endYear;
    private Boolean serial;
    private Boolean shortFilm;
    private Boolean completed;
}
