package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {



    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        Library BibliotecaLibrary = new Library(System.in, System.out, inputScanner);
        BibliotecaLibrary.load();

    }
}
