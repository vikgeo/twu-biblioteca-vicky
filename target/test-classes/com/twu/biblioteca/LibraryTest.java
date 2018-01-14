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
    public void initializeInputAndOutputSteamAndLibrary() {
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
    public String scanAndSaveInputsToString(Scanner inputs, StringBuilder stringBuilder) {
        while (inputs.hasNext()) {
            stringBuilder.append(inputs.next().toString());
        }
        return stringBuilder.toString();
    }

    @Test
    public void testGreeting() {
        library.printGreeting();
        outputWatch.flush();

        String whatWasPrinted = outputByteStream.toString();

        assertEquals(StringsUsed.GREETING + "\n", whatWasPrinted);

    }

    @Test
    public void testLoad() {
        library.load();
        outputWatch.flush();
        String whatWasPrinted = outputByteStream.toString();

        assertEquals(StringsUsed.GREETING + "\n1. " +
                "Book List\n" + "1. The Great Gatsby F. Scott Fitzgerald 1925\n", whatWasPrinted);

    }

    @Test
    public void testBookList() {
        library.loadBookList();
        outputWatch.flush();
        String bookListPrinted = outputByteStream.toString();

        assertEquals("1. The Great Gatsby F. Scott Fitzgerald 1925\n", bookListPrinted);
    }

    @Test

    public void testLoadMenu() {
        library.loadMenuOptions();
        outputWatch.flush();
        String menuOptionsPrinted = outputByteStream.toString();
        assertEquals("1. Book List\n", menuOptionsPrinted);

    }

    @Test
    public void testMenuChoiceInput() {
        library.loadMenuOptions();
        String inputsReceived = scanAndSaveInputsToString(initialInputsScanner, inputStringBuilder);
        assertEquals("1", inputsReceived);

    }

    @Test
    public void testCorrectMenuSelectionParsing() {
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
    public void testWrongMenuSelectionParsing() {
        String parsedMenuSelection = "We have kittens";
        library.returnMenuSelection(parsedMenuSelection);
        outputWatch.flush();
        String whatWasPrinted = outputByteStream.toString();

        assertEquals(StringsUsed.INVALID_MENU_SELECTION + "\n", whatWasPrinted);

    }


    @Mock
    private Library mockLibrary = mock(Library.class);

    @Test
    public void testClosingStreams() {
        String parsedMenuSelection = "Q";
        mockLibrary.returnMenuSelection(parsedMenuSelection);
        //need to figure out a way to test it actually quits
    }
}