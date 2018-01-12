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




    public Library(InputStream input, PrintStream output, Scanner inputScanner){
        this.output = output;
        this.input = input;
        this.inputScanner = inputScanner;
    }

    public Library(InputStream input, PrintStream output, Scanner inputScanner, ArrayList<Book> bookList){
        this.output = output;
        this.input = input;
        this.bookList = bookList;
        this.inputScanner = inputScanner;
    }

    public void load(){
        printGreeting();
        loadMenuOptions();
        returnMenuSelection(parseMenuOptionSelection());

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
        StringBuilder inputStringBuilder = new StringBuilder();

        while (inputScanner.hasNext()){
            inputStringBuilder.append(inputScanner.next().toString());
        }

        return inputStringBuilder.toString();

    }

    public void returnMenuSelection(String userMenuSelection) {
        switch (userMenuSelection){
            case "1":
                loadBookList();
                break;
            case "9":
                output.println("Thank you for using Biblioteca!");
                quitApplication();
            default:
                output.println("Select a valid option!");

            }
        }

    protected void closeAllStreams()  {
        inputScanner.remove();
        inputScanner.close();
        output.close();
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quitApplication() {
        System.exit(0);
    }
}
