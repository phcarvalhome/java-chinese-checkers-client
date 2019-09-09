package com.phcarvalho.model.util;

public class LogUtil {

    public static void logInformation(String message, String title){
        System.out.println("[INFO] " + title + " >>> " + message);
    }

    public static void logError(String message, String title) {
        System.out.println("[ERROR] " + title + " >>> " + message);
    }

    public static void logError(String message, String title, Exception exception){
        logError(message, title);
        exception.printStackTrace();
    }
}
