package com.movies.moviesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Award {
    private String name;
    private Boolean win;
    private String imageUrl;
    private String nominationName;
    private Integer year;
    private List<Person> awardPerson;
}
