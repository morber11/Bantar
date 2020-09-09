package com.bantar.bantar.utils;

public class ExceptionUtil {
    public static void handle(Exception e) {
        // this doesn't do anything... yet
        System.out.println("An error occured:\n");
        e.printStackTrace();
    }

    // TODO: Make this work
    private static void writeErrorToFile() {

    }
}
