import java.util.Arrays;
import java.util.List;

public class QuestionFactory {
    public static Question create(String text, int score, String correct, String optionsRaw) {
        List<String> options = (optionsRaw != null)
                ? Arrays.asList(optionsRaw.split(","))
                : null;

        return new MultipleChoiceQuestion(text, score, correct, options);
    }
}