package com.twu.biblioteca;

import java.util.ArrayList;

public class Book {
    private String bookTitle;
    private String bookAuthorName;
    private String yearPublished;



    public Book(String bookTitle, String authorName, String yearPublished){
        this.bookTitle = bookTitle;
        this.bookAuthorName = authorName;
        this.yearPublished = yearPublished;

    }


    public String getTitle() {
        return this.bookTitle;
    }

    public String getAuthorName() {
        return this.bookAuthorName;
    }

    public String getYearPublished() {
        return yearPublished;
    }

}
