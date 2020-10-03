package handler;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.*;

public class WordCounter {

    private Map<String, Integer> map = new HashMap<>();
    private OkHttpClient okHttpClient = new OkHttpClient();
    private static WordCounter wordCounter;

    private WordCounter() {
    }

    public static WordCounter instance() {
        if (wordCounter == null) {
            wordCounter = new WordCounter();
        }
        return wordCounter;
    }

    public void enteredWord() throws MyException {
        String word;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the word");
        while (scanner.hasNextLine()) {
            word = scanner.nextLine();
            score(word);
        }
        scanner.close();
    }

    private String getDocument(String path) throws MyException {
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (response.code() == HttpURLConnection.HTTP_OK) {
                return response.body().string();
            } else {
                throw new MyException(String.format("Document not found. Path: %s.", path));
            }
        } catch (IOException e) {
            throw new MyException(e);
        }
    }

    private void score(String word) throws MyException {
        int quantity = 0;
        String html = "https://mail.ru/";

        String text = Jsoup.parse(getDocument(html)).text();

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
