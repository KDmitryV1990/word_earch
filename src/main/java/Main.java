
import java.util.*;

public class Main {

    private static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        WordCounter wordCount = WordCounter.instance();
        try {
            wordCount.enteredWord();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
