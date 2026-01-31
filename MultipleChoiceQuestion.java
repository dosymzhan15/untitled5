import java.util.List;

class MultipleChoiceQuestion extends Question {
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
}

