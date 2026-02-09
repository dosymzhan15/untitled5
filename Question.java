import java.util.Objects;

public abstract class Question {
    private String text;
    private int score;
    private String correctAnswer;

    public Question(String text, int score, String correctAnswer) {
        this.text = text;
        this.score = score;
        this.correctAnswer = correctAnswer;
    }

    public String getText() { return text; }
    public int getScore() { return score; }
    public String getCorrectAnswer() { return correctAnswer; }

    public abstract void display();

    public boolean checkAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            throw new InvalidAnswerException("Жауап бос болмауы керек!");
        }
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }

    @Override
    public String toString() { return "Сұрақ: " + text + " [" + score + " балл]"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return score == question.score && Objects.equals(text, question.text);
    }

    @Override
    public int hashCode() { return Objects.hash(text, score); }
}