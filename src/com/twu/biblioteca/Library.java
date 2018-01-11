package com.twu.biblioteca;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.lang.System.*;

public class Library {

    protected InputStream input;
    protected PrintStream output;
    private static String _greeting = "Hello, library user!";

    public Library(InputStream input, PrintStream output){
        this.output = output;
        this.input = input;
    }


    protected void printGreeting(){
        output.println(_greeting);
    }


}
