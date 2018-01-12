package com.twu.biblioteca;


import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class LibraryTest {



    private ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream();
    private PrintStream outputWatch = new PrintStream(outputByteStream);
    ArrayList<String> greatGatsby = new ArrayList<>(Arrays.asList("The Great Gatsby", "F. Scott Fitzgerald", "1925"));
    ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(new Book(greatGatsby)));
    private Library library = new Library(System.in, outputWatch, bookList);


    String correctInput = "1";
    InputStream inputByteStream = new ByteArrayInputStream(correctInput.getBytes(StandardCharsets.UTF_8));
    Scanner inputScanner = new Scanner(inputByteStream).useDelimiter("'");
    StringBuilder inputStringBuilder = new StringBuilder();



    @Resource
    public String scanAndSaveInputsToString(){
        while (inputScanner.hasNext()){
            inputStringBuilder.append(inputScanner.next().toString());
        }
        return inputStringBuilder.toString();
    }

    @Test
    public void testGreeting()  {
        library.printGreeting();
        outputWatch.flush();

        String whatWasPrinted = outputByteStream.toString();

        assertEquals("Hello, library user!\nPlease type a number to choose one of the following options:\n", whatWasPrinted);

    }

    @Test
    public void testLoad() {
        library.load();
        outputWatch.flush();
        String whatWasPrinted = outputByteStream.toString();

        assertEquals("Hello, library user!\nPlease type a number to choose one of the following options:\n1. Book List\n", whatWasPrinted);

    }

    @Test
    public void testBookList(){
        library.loadBookList();
        outputWatch.flush();
        String bookListPrinted = outputByteStream.toString();

        assertEquals("1. The Great Gatsby F. Scott Fitzgerald 1925\n", bookListPrinted);
    }

    @Test

    public void testLoadMenu(){
        library.loadMenuOptions();
        outputWatch.flush();
        String menuOptionsPrinted = outputByteStream.toString();
        assertEquals("1. Book List\n", menuOptionsPrinted);

    }

    @Test
    public void testMenuChoiceInput(){
        library.loadMenuOptions();
        String inputsReceived = scanAndSaveInputsToString();
        assertEquals("1", inputsReceived);
        
    }

    @Test
    public void testCorrectMenuParsing(){
        //library.loadMenuOptions();
        String parsedMenuSelection = library.parseMenuOptionSelection();

        assertEquals("1", parsedMenuSelection);


    }


}


