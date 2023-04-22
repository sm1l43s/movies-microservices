package com.movies.moviesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Integer kinopoiskId;
    private String type;
    private String date;
    private Integer positiveRating;
    private Integer negativeRating;
    private String author;
    private String title;
    private String description;
}
