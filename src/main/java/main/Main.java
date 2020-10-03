package main;

import handler.MyException;
import handler.WordCounter;

public class Main {

    public static void main(String[] args) {
        WordCounter wordCount = WordCounter.instance();
        try {
            wordCount.enteredWord();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
