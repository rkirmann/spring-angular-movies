package com.movies.test.service;

import com.movies.test.AbstractTest;
import com.movies.test.dto.SearchDto;
import com.movies.test.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class MovieServiceTest extends AbstractTest {

    @Autowired
    MovieService movieService;

    @MockBean
    RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        Movie movie = new Movie(1L, "title", 1999, "1");
        movieRepository.save(movie);
    }

    @AfterEach
    public void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void list() {
        List<Movie> movies = movieService.list();
        assertThat(movies.size()).isEqualTo(1);
        assertThat(movies.get(0).getImdbID()).isEqualTo("1");
    }

    @Test
    void save() {
        movieService.save(new Movie());
        List<Movie> movies = movieService.list();
        assertThat(movies.size()).isEqualTo(2);
    }

    @Test
    void delete() {
        movieService.delete("1");
        List<Movie> movies = movieService.list();
        assertThat(movies).isEmpty();
    }

    @Test
    void searchMovies() {
        SearchDto searchDto = new SearchDto();
        Mockito.lenient().when(restTemplate.getForObject(anyString(), any())).thenReturn(searchDto);
        SearchDto response = movieService.searchMovies("test", 1);
        assertThat(response).isEqualTo(searchDto);
    }
}
