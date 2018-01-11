package com.twu.biblioteca;


import org.junit.AfterClass;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class LibraryTest {


    private InputStream inputWatch = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };

    private ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

    private PrintStream outputWatch = new PrintStream(byteStream);

    private Library library = new Library(inputWatch, outputWatch);


    @Test
    public void testGreeting()  {
        library.printGreeting();
        outputWatch.flush();

        String whatWasPrinted = byteStream.toString();

        assertEquals("Hello, library user!\n", whatWasPrinted);

    }

    @Test
    public void testLoad() {
        library.load();
        outputWatch.flush();
        String whatWasPrinted = byteStream.toString();

        assertEquals("Hello, library user!\n", whatWasPrinted);

    }

    @Test

    public void testBookList(){
        library.loadBookList();
        outputWatch.flush();
        String bookListPrinted = byteStream.toString();

        assertEquals("1. The Great Gatsby\n", bookListPrinted);
    }

    @Test

    public void testLoadMenu(){
        library.loadMenuOptions();
        outputWatch.flush();
        String menuOptionsPrinted = byteStream.toString();
        assertEquals("1. Book List\n", menuOptionsPrinted);

    }
}


