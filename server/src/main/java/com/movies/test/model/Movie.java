package com.movies.test.model;

import com.movies.test.dto.MovieDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Integer year;
    private String imdbID;

    public Movie(MovieDto movieDto) {
        this.title = movieDto.getTitle();
        this.year = movieDto.getYear();
        this.imdbID = movieDto.getImdbID();
    }
}
