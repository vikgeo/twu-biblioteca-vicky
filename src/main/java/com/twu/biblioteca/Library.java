package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Library {

    protected InputStream input;
    protected PrintStream output;
    private static String _greeting = "Hello, library user!\nPlease type a number to choose one of the following options:";
    private static String[] _menuOptions = {"Book List"};
    private Scanner inputScanner;
    protected ArrayList<Book> availableBooksList = new ArrayList<>();;
    protected ArrayList<Book> checkedOutBooksList = new ArrayList<>();




    public Library(InputStream input, PrintStream output, Scanner inputScanner){
        this.output = output;
        this.input = input;
        this.inputScanner = inputScanner;
    }

    public Library(InputStream input, PrintStream output, Scanner inputScanner, ArrayList<Book> bookList){
        this.output = output;
        this.input = input;
        this.availableBooksList = bookList;
        this.inputScanner = inputScanner;
    }

    public void load(){
        printGreeting();
        loadMenuOptions();
        returnMenuSelection(parseKeyboardInputs());

    }

    public ArrayList<Book> getAvailableBooksList(){
        return availableBooksList;
    }

    public ArrayList<Book> getCheckedOutBooksList(){
        return checkedOutBooksList;
    }


    protected void printGreeting(){
        output.println(_greeting);
    }


    protected void loadBookList() {
        int listPosition = 1;
        for (Book book: availableBooksList
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

    protected String parseKeyboardInputs() {
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


    public void quitApplication() {
        System.exit(0);
    }



    public Book checkIfBookOnBookList(String bookToCheckOut) {
        Book bookFound = null;
        for (Iterator<Book> i = getAvailableBooksList().iterator(); i.hasNext();) {
            Book book = i.next();
            if (book.getTitle().equals(bookToCheckOut)){
                getCheckedOutBooksList().add(book);
                bookFound = book;
                break;
            }
        }
        return bookFound;
    }

    public void checkOutBook(Book bookToCheckOut) {
        if (bookToCheckOut == null) {
            output.println("That book is not available.");
        } else {
            availableBooksList.remove(bookToCheckOut);
            checkedOutBooksList.add(bookToCheckOut);
            output.println("Thank you! Enjoy the book");

        }
    }


    public void returnBook(Book bookToReturn) {
        if (bookToReturn == null) {
            output.println("That is not a valid book to return.");
        } else {
            checkedOutBooksList.remove(bookToReturn);
            availableBooksList.add(bookToReturn);
            output.println("Thank you for returning the book.");

        }
    }
}
