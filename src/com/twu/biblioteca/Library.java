package com.twu.biblioteca;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.lang.System.*;

public class Library {

    protected InputStream input;
    protected PrintStream output;
    private static String _greeting = "Hello, library user!";
    private static String[] _bookList = {"The Great Gatsby"};
    private static String[] _menuOptions = {"Book List"};


    public Library(InputStream input, PrintStream output){
        this.output = output;
        this.input = input;
    }

    public void load(){
        printGreeting();
    }


    protected void printGreeting(){
        output.println(_greeting);
    }


    protected void loadBookList() {
        int listPosition = 1;
        for (String book:_bookList
                ) {
            output.print(listPosition + ". " + book + "\n");
        }
    }

    public void loadMenuOptions() {

        int listPosition = 1;
        for (String book : _menuOptions
                ) {
            output.print(listPosition + ". " + book + "\n");

        }
    }

}
