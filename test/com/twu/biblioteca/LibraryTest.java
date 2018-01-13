package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.mock;

public class LibraryTest {

    private PrintStream outputWatch;
    private Scanner initialInputsScanner;
    private StringBuilder inputStringBuilder;
    private ByteArrayOutputStream outputByteStream;
    Library library;
    Book gatsbyBook;
    ArrayList<Book> bookList;


    @Before
    public void initializeInputAndOutputSteamAndLibrary(){
        outputByteStream = new ByteArrayOutputStream();
        outputWatch = new PrintStream(outputByteStream);
        ArrayList<String> greatGatsby = new ArrayList<>(Arrays.asList("The Great Gatsby", "F. Scott Fitzgerald", "1925"));
        gatsbyBook = new Book(greatGatsby);
        bookList = new ArrayList<>(Arrays.asList(gatsbyBook));

        String initialInputs = "1";
        InputStream initialInputByteStream = new ByteArrayInputStream(initialInputs.getBytes(StandardCharsets.UTF_8));
        initialInputsScanner = new Scanner(initialInputByteStream).useDelimiter("'");
        library = new Library(System.in, outputWatch, initialInputsScanner, bookList);


       inputStringBuilder = new StringBuilder();
    }


    @Resource
    public String scanAndSaveInputsToString(Scanner inputs, StringBuilder stringBuilder){
        while (inputs.hasNext()){
            stringBuilder.append(inputs.next().toString());
        }
        return stringBuilder.toString();
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
        String inputsReceived = scanAndSaveInputsToString(initialInputsScanner, inputStringBuilder);
        assertEquals("1", inputsReceived);
        
    }

    @Test
    public void testCorrectMenuSelectionParsing(){
        String parsedMenuSelection = library.parseKeyboardInputs();
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


    @Mock
    private Library mockLibrary = mock(Library.class);

    @Test
    public void testClosingStreams(){
        String parsedMenuSelection = "9";
        mockLibrary.returnMenuSelection(parsedMenuSelection);
        //need to think of a way to test it actually quits
    }

    @Test
    public void testFindingBookOnList(){

        String initialInputs = "The Great Gatsby";
        InputStream initialInputByteStream = new ByteArrayInputStream(initialInputs.getBytes(StandardCharsets.UTF_8));
        Scanner bookInputsScanner = new Scanner(initialInputByteStream).useDelimiter("'");
        Library libraryBookCheckout = new Library(System.in, outputWatch, bookInputsScanner, bookList);

        String bookParsed = libraryBookCheckout.parseKeyboardInputs();
        Book bookToCkeckout = libraryBookCheckout.checkIfBookOnBookList(bookParsed);

        assertEquals(bookToCkeckout, gatsbyBook);

    }

    @Test
    public void testRemovingBookFromAvailableListAndAddingToCkeckedOut() {

        String initialInputs = "The Great Gatsby";
        InputStream initialInputByteStream = new ByteArrayInputStream(initialInputs.getBytes(StandardCharsets.UTF_8));
        Scanner bookInputsScanner = new Scanner(initialInputByteStream).useDelimiter("'");
        Library libraryBookCheckout = new Library(System.in, outputWatch, bookInputsScanner, bookList);

        String bookParsed = libraryBookCheckout.parseKeyboardInputs();
        Book bookToCkeckout = libraryBookCheckout.checkIfBookOnBookList(bookParsed);
        libraryBookCheckout.checkOutBook(bookToCkeckout);


        assertTrue(libraryBookCheckout.getAvailableBooksList().isEmpty());
        assertTrue(libraryBookCheckout.getCheckedOutBooksList().contains(gatsbyBook));


    }

    @Test
    public void testRemovingBookFromCheckedOutListAndAddingThemAvailable() {

        String initialInputs = "The Great Gatsby";
        InputStream initialInputByteStream = new ByteArrayInputStream(initialInputs.getBytes(StandardCharsets.UTF_8));
        Scanner bookInputsScanner = new Scanner(initialInputByteStream).useDelimiter("'");
        Library libraryBookCheckout = new Library(System.in, outputWatch, bookInputsScanner, bookList);

        String bookParsed = libraryBookCheckout.parseKeyboardInputs();
        Book bookToReturn = libraryBookCheckout.checkIfBookOnBookList(bookParsed);
        libraryBookCheckout.returnBook(bookToReturn);


        assertTrue(libraryBookCheckout.getAvailableBooksList().contains(gatsbyBook));
        assertTrue(libraryBookCheckout.getCheckedOutBooksList().isEmpty());


    }

}


