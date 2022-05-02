package com.movies.test.rest;

import com.movies.test.dto.MovieDto;
import com.movies.test.dto.SearchDto;
import com.movies.test.model.Movie;
import com.movies.test.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/search/{title}")
    public SearchDto search(@PathVariable String title, @PathParam("page") int page) {
        return movieService.searchMovies(title, page);
    }

    @GetMapping(value = "/get")
    public List<MovieDto> get() {
        return movieService.list().stream().map(MovieDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Void> save(@RequestBody MovieDto movieDto) {
        movieService.save(new Movie(movieDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{imdbId}")
    public ResponseEntity<Void> delete(@PathVariable String imdbId) {
        movieService.delete(imdbId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
