package com.movies.test;

import com.movies.test.repository.MovieRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(locations="classpath:application-test.properties")
public abstract class AbstractTest {

    @Autowired
    protected MovieRepository movieRepository;




}
