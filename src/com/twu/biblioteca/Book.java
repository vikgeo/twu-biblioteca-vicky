package com.twu.biblioteca;

import java.util.ArrayList;

public class Book {
    private String bookTitle;
    private String bookAuthorName;
    private String yearPublished;

    public Book(ArrayList<String> bookData){
        this.bookTitle = bookData.get(0);
        this.bookAuthorName = bookData.get(1);
        this.yearPublished = bookData.get(2);

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
