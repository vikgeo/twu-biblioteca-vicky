package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BookTest {
    ArrayList<String> greatGatsby = new ArrayList<>(Arrays.asList("The Great Gatsby", "F. Scott Fitzgerald", "1925"));

    Book testBook = new Book(greatGatsby);

    @Test
    public void testGetTitle(){
        String testTitle = testBook.getTitle();
        assertEquals("The Great Gatsby", testTitle);
    }

    @Test
    public void testGetAuthor(){
        String testAuthor = testBook.getAuthorName();
        assertEquals("F. Scott Fitzgerald", testAuthor);
    }

    @Test
    public void testGetYearPublished(){
        String testYearPublished = testBook.getYearPublished();
        assertEquals("1925", testYearPublished);
    }

}
