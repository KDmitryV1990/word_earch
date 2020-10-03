import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    private Map<String, Integer> map = new HashMap<>();

    private WordCounter() {
    }

    private static WordCounter wordCounter;

    public static WordCounter instance() {
        if (wordCounter == null) {
            wordCounter = new WordCounter();
        }
        return wordCounter;
    }

    public void enteredWord() {
        String word;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the word");
        while (scanner.hasNextLine()) {
            word = scanner.nextLine();
            score(word);

        }
        scanner.close();
    }

    private void score(String word) {
//        Map<String, Integer> map = new HashMap<>();
        int quantity = 0;
        String html = "https://mail.ru/";
        Document doc = null;
        try {
            doc = Jsoup.connect(html)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = doc.body().wholeText();
//        String text =  Jsoup.parse("https://mail.ru/").text();
//        doc = Jsoup.parseBodyFragment(html);
//        String fullText = doc.html();
//        System.out.println(fullText);

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
