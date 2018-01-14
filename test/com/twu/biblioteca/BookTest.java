package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BookTest {


    Book testBook = new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925");
    Book secondTestBook = new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925");

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
