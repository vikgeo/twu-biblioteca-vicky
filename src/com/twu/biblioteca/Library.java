package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    protected InputStream input;
    protected PrintStream output;
    private static String _greeting = "Hello, library user!\nPlease type a number to choose one of the following options:";
    ArrayList<Book> bookList;
    private static String[] _menuOptions = {"Book List"};
    private Scanner inputScanner;




    public Library(InputStream input, PrintStream output){
        this.output = output;
        this.input = input;
        inputScanner = new Scanner(input);
    }

    public Library(InputStream input, PrintStream output, ArrayList<Book> bookList){
        this.output = output;
        this.input = input;
        this.bookList = bookList;
        inputScanner = new Scanner(input);
    }

    public void load(){
        printGreeting();
        loadMenuOptions();
        parseMenuOptionSelection();

    }


    protected void printGreeting(){
        output.println(_greeting);
    }


    protected void loadBookList() {
        int listPosition = 1;
        for (Book book:bookList
                ) {
            output.print(listPosition + ". " + book.getTitle() + " " + book.getAuthorName() + " " +
                    book.getYearPublished()  +"\n");
            listPosition++;
        }
    }

    protected void loadMenuOptions() {

        int listPosition = 1;
        for (String menuOption : _menuOptions
                ) {
            output.print(listPosition + ". " + menuOption + "\n");
            listPosition++;

        }
    }

    protected String parseMenuOptionSelection() {
        //StringBuilder inputStringBuilder = new StringBuilder();

//        if (inputScanner.hasNext()){
//        } else {
//            while (inputScanner.hasNext()){
//                inputStringBuilder.append(inputScanner.next().toString());
//            }
//        }return inputStringBuilder.toString();

        int selection;

        try {
            selection = inputScanner.nextInt();
        } catch (Exception e) {
            selection = -1;
            output.println("Select a valid option!");
            inputScanner.next(); // discard the input
        }
        return String.valueOf(selection);
    }
}
