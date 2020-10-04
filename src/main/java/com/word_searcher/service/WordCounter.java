package com.word_searcher.service;

import org.jsoup.Jsoup;

import java.util.*;


public class WordCounter {

    private Map<String, Integer> map = new HashMap<>();
    private static WordCounter wordCounter;

    private WordCounter() {
    }

    public static WordCounter instance() {
        if (wordCounter == null) {
            wordCounter = new WordCounter();
        }
        return wordCounter;
    }

    public void searchWord(String document) {
        String word;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the word");
        while (scanner.hasNextLine()) {
            word = scanner.nextLine();
            count(word, document);
        }
        scanner.close();
    }


    private void count(String word, String document) {
        int quantity = 0;
        String html = "https://news.yandex.ru/computers.html";

        String text = Jsoup.parse(document).text();

        String[] wordArray = text.split("[^a-zA-Z_0-9а-яёА-ЯЁ]");
        for (String element : wordArray) {
            if (element.equalsIgnoreCase(word)) {
                quantity++;
            }
        }
        map.put(word, quantity);
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}
