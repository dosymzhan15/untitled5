import java.util.*;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class Exam {
    private String title;
    private List<Question> questions;
    private Candidate candidate;
    private QuestionRepository repository;

    public Exam(String title, Candidate candidate, QuestionRepository repository) {
        this.title = title;
        this.candidate = candidate;
        this.repository = repository;
        this.questions = new ArrayList<>();
    }

    public void loadQuestions() throws DatabaseException {
        // Использование Lambda и Generics (Критерий 15)
        this.questions = repository.getAllQuestions().stream()
                .sorted(Comparator.comparingInt(Question::getScore))
                .collect(Collectors.toList());
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int obtainedScore = 0;
        int totalPossible = 0;

        System.out.println("=== " + title + " ===");

        for (Question q : questions) {
            totalPossible += q.getScore();
            q.display();
            System.out.print("Жауабыңыз: ");
            String ans = scanner.nextLine();

            try {
                if (q.checkAnswer(ans)) {
                    System.out.println("Дұрыс!");
                    obtainedScore += q.getScore();
                } else {
                    System.out.println("Қате. Дұрыс жауап: " + q.getCorrectAnswer());
                }
            } catch (InvalidAnswerException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nҚорытынды: " + obtainedScore + " / " + totalPossible);

        try {
            repository.saveUserResult(candidate.getName(), obtainedScore);
            System.out.println("Нәтиже сақталды.");
        } catch (DatabaseException e) {
            System.out.println("Қате: " + e.getMessage());
        }

        // Генерация JSON (Критерий 11)
        JSONObject report = new JSONObject();
        report.put("student", candidate.getName());
        report.put("finalScore", obtainedScore);
        System.out.println("JSON Report: " + report.toString());
    }
}