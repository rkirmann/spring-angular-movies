package com.movies.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDto implements Serializable {
    @JsonProperty("Search")
    private List<MovieDto> search;

    @JsonProperty("Response")
    @JsonFormat(with={ JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
    private boolean isResponseOk;
    @JsonProperty("Error")
    private String error;

    private Integer totalResults;

    private int page;

    public SearchDto(String error) {
        this.error = error;
    }
}
