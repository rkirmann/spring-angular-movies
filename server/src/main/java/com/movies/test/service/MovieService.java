package com.movies.test.service;

import com.movies.test.dto.SearchDto;
import com.movies.test.model.Movie;
import com.movies.test.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class.getName());

    @Value("${omdb.api}")
    private String api;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public List<Movie> list() {
        return movieRepository.findAll();
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional
    public void delete(String imdbId) {
        movieRepository.deleteByImdbID(imdbId);
    }

    public SearchDto searchMovies(String title, int page) {
        logger.info("searching title {}, page {}", title, page);
        SearchDto searchDto =
                Optional.ofNullable(restTemplate.getForObject(String.format(api, title, page), SearchDto.class))
                .orElse(new SearchDto("No response from OMDB")) ;
        searchDto.setPage(page);
        return searchDto;
    }

}
