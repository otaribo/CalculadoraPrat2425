package main.java.calculator.logic;

import java.util.ArrayList;
import java.util.List;

public class History {
    private static List<String> history = new ArrayList<>();

    public static void addEntry(String operation, String result) {
        history.add(operation + " = " + result);
    }

    public static void clearHistory() {
        history.clear();
    }
}

