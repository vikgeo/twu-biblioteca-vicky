package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class LibraryTest {

    InputStream inputWatch = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };

    ByteArrayOutputStream greetingStream = new ByteArrayOutputStream();

    PrintStream outputWatch = new PrintStream(greetingStream);

    Library library = new Library(inputWatch, outputWatch);


    @Test
    public void testGreeting()  {
        library.printGreeting();
        outputWatch.flush();

        String whatWasPrinted = greetingStream.toString();

        assertEquals("Hello, library user!\n", whatWasPrinted);

    }

}
