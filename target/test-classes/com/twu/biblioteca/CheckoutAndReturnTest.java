package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;

public class CheckoutAndReturnTest {

    private PrintStream outputWatch;
    private Scanner initialInputsScanner;
    private StringBuilder inputStringBuilder;
    private ByteArrayOutputStream outputByteStream;
    Library library;
    Book gatsbyBook;
    ArrayList<Book> bookList;

    @Before
    public void testSetup() {

        outputByteStream = new ByteArrayOutputStream();
        outputWatch = new PrintStream(outputByteStream);
        ArrayList<String> greatGatsby = new ArrayList<>(Arrays.asList("The Great Gatsby", "F. Scott Fitzgerald", "1925"));
        gatsbyBook = new Book(greatGatsby);
        bookList = new ArrayList<>(Arrays.asList(gatsbyBook));

        String initialInputs = "The Great Gatsby";
        InputStream initialInputByteStream = new ByteArrayInputStream(initialInputs.getBytes(StandardCharsets.UTF_8));
        Scanner bookInputsScanner = new Scanner(initialInputByteStream).useDelimiter("'");
        library = new Library(System.in, outputWatch, bookInputsScanner, bookList);

    }

    @Test
    public void testFindingBookOnList(){


        String bookParsed = library.parseKeyboardInputs();
        Book bookToCkeckout = library.checkIfBookOnBookList(bookParsed);

        assertEquals(bookToCkeckout, gatsbyBook);

    }

    @Test
    public void testRemovingBookFromAvailableListAndAddingToCheckedOut() {

        String bookParsed = library.parseKeyboardInputs();
        Book bookToCkeckout = library.checkIfBookOnBookList(bookParsed);
        library.checkOutBook(bookToCkeckout);


        assertTrue(library.getAvailableBooksList().isEmpty());
        assertTrue(library.getCheckedOutBooksList().contains(gatsbyBook));


    }

    @Test
    public void testMessageSuccessfulCheckout(){
        library.returnBook(gatsbyBook);
        outputWatch.flush();
        String messagePrinted = outputByteStream.toString();

        assertEquals(StringsUsed.SUCCESSFUL_RETURN + "\n", messagePrinted);
    }


    @Test
    public void testMessageFailedReturn(){
        library.checkOutBook(library.checkIfBookOnBookList("Great Expectations"));
        outputWatch.flush();
        String messagePrinted = outputByteStream.toString();

        assertEquals(StringsUsed.UNAVAILABLE_BOOK + "\n", messagePrinted);
    }



    @Test
    public void testRemovingBookFromCheckedOutListAndAddingThemAvailable() {

        String bookParsed = library.parseKeyboardInputs();
        Book bookToReturn = library.checkIfBookOnBookList(bookParsed);
        library.returnBook(bookToReturn);


        assertTrue(library.getAvailableBooksList().contains(gatsbyBook));
        assertTrue(library.getCheckedOutBooksList().isEmpty());


    }

    @Test
    public void testMessageSuccessfulReturn(){
        library.checkOutBook(gatsbyBook);
        outputWatch.flush();
        String messagePrinted = outputByteStream.toString();

        assertEquals(StringsUsed.SUCCESSFUL_CHECKOUT + "\n", messagePrinted);
    }


    @Test
    public void testMessageFailedCheckout() {
        library.returnBook(library.checkIfBookOnBookList("Great Expectations"));
        outputWatch.flush();
        String messagePrinted = outputByteStream.toString();

        assertEquals(StringsUsed.CANNOT_RETURN + "\n", messagePrinted);
    }

}
