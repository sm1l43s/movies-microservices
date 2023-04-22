package com.movies.moviesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episode {
    private Integer seasonNumber;
    private Integer episodeNumber;
    private String nameRu;
    private String nameEn;
    private String synopsis;
    private String releaseDate;
}
