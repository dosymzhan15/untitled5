import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;

    public MultipleChoiceQuestion(String text, int score, String correctAnswer, List<String> options) {
        super(text, score, correctAnswer);
        this.options = options;
    }

    @Override
    public void display() {
        System.out.println("\n[Тест] " + getText() + " (Балл: " + getScore() + ")");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    // JSON функционал для API (Критерий 11)
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("text", getText());
        json.put("score", getScore());
        json.put("options", new JSONArray(options));
        return json;
    }
}