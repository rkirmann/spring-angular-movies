package com.movies.test.dto;

import com.movies.test.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MovieDto implements Serializable {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private Integer year;

    private String imdbID;

    public MovieDto(Movie movie) {
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.imdbID = movie.getImdbID();
    }
}
