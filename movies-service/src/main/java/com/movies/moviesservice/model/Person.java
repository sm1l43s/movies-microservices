package com.movies.moviesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer kinopoiskId;
    private String webUrl;
    private String nameRu;
    private String nameEn;
    private String sex;
    private String posterUrl;
    private Integer growth;
    private String birthday;
    private String death;
    private Integer age;
    private String birthplace;
    private String deathplace;
    private String profession;
}
