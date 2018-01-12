package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.

public class LibraryTest {

    private PrintStream outputWatch;
    private Scanner initialInputsScanner;
    private StringBuilder inputStringBuilder;
    private ByteArrayOutputStream outputByteStream;
    Library library;


    @Before
    public void initializeInputAndOutputSteamAndLibrary(){
        outputByteStream = new ByteArrayOutputStream();
        outputWatch = new PrintStream(outputByteStream);
        ArrayList<String> greatGatsby = new ArrayList<>(Arrays.asList("The Great Gatsby", "F. Scott Fitzgerald", "1925"));
        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(new Book(greatGatsby)));

        String initialInputs = "1";
        InputStream initialInputByteStream = new ByteArrayInputStream(initialInputs.getBytes(StandardCharsets.UTF_8));
        initialInputsScanner = new Scanner(initialInputByteStream).useDelimiter("'");
        library = new Library(System.in, outputWatch, initialInputsScanner, bookList);


       inputStringBuilder = new StringBuilder();
    }


    @Resource
    public String scanAndSaveInputsToString(){
        while (initialInputsScanner.hasNext()){
            inputStringBuilder.append(initialInputsScanner.next().toString());
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

        assertEquals("Hello, library user!\nPlease type a number to choose one of the following options:\n1. " +
                "Book List\n" + "1. The Great Gatsby F. Scott Fitzgerald 1925\n", whatWasPrinted);

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
    public void testCorrectMenuSelectionParsing(){
        String parsedMenuSelection = library.parseMenuOptionSelection();
        assertEquals("1", parsedMenuSelection);

    }

    @Test
    public void testCorrectMenuSelection() {
        String parsedMenuSelection = "1";
        library.returnMenuSelection(parsedMenuSelection);
        outputWatch.flush();
        String whatWasPrinted = outputByteStream.toString();

        assertEquals("1. The Great Gatsby F. Scott Fitzgerald 1925\n", whatWasPrinted);
    }

    @Test
    public void testWrongMenuSelectionParsing(){
        String parsedMenuSelection = "We have kittens";
        library.returnMenuSelection(parsedMenuSelection);
        outputWatch.flush();
        String whatWasPrinted = outputByteStream.toString();

        assertEquals("Select a valid option!\n", whatWasPrinted);

    }

//    @Test
//    public void testQuittingApp(){
//        library.quitApplication();
//        assertNull(library);
 //   }

    @Test
    public void testClosingStreams(){
        library.closeAllStreams();
        assertNull(outputWatch);
    }
//
//    @Test
//    public void testAppCloseInputStream(){
//        String parsedMenuSelection = "9";
//        library.returnMenuSelection(parsedMenuSelection);
//        assertNull(System.in);
//        assertNull(initialInputsScanner);
//    }
//
//    @Test
//    public void testAppCloseOutputStream(){
//        String parsedMenuSelection = "9";
//        library.returnMenuSelection(parsedMenuSelection);
//        assertNull(System.in);
//    }





}


