package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class MovieTest {

    Movie testMovie;

    @Before
    public void testSetup(){
        testMovie = new Movie("Batman begins", "2005", "Christopher Nolan", 8);
    }

    @Test
    public void testGetTitle(){
        String titleTest= testMovie.getTitle();

        assertEquals("Batman begins", titleTest);
    }

    @Test
    public void testGetYear(){
        String yearTest = testMovie.getYear();

        assertEquals("2005", yearTest);
    }

    @Test
    public void testGetDirector(){
        String testDirector = testMovie.getDirector();

        assertEquals("Christopher Nolan", testDirector);
    }

    @Test
    public void testGetRating(){
        int testRating = testMovie.getRating();

        assertEquals(8, testRating);
    }

}
