package com.movies.test.rest;

import com.movies.test.AbstractTest;
import com.movies.test.dto.MovieDto;
import com.movies.test.dto.SearchDto;
import com.movies.test.model.Movie;
import com.movies.test.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

class PublicControllerTest extends AbstractTest {

    @Autowired
    PublicController publicController;

    @MockBean
    MovieService movieService;

    @Test
    void search() {
        SearchDto searchDto = new SearchDto();
        Mockito.when(movieService.searchMovies(anyString(), anyInt())).thenReturn(searchDto);
        assertThat(publicController.search("test", 1)).isEqualTo(searchDto);
    }

    @Test
    void get() {
        List<Movie> movies = List.of(new Movie(1L, "test", 1999, "1"));
        Mockito.when(movieService.list()).thenReturn(movies);
        assertThat(publicController.get().get(0).getTitle()).isEqualTo("test");
    }

    @Test
    void save() {
        Mockito.doNothing().when(movieService).save(any());
        publicController.save(new MovieDto());
        Mockito.verify(movieService).save(any());
    }

    @Test
    void delete() {
        Mockito.doNothing().when(movieService).delete(anyString());
        publicController.delete("1");
        Mockito.verify(movieService).delete(anyString());
    }
}
