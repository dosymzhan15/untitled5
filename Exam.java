import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Exam{
    private String title;
    private List<Question> questions;
    private Candidate candidate;

    public Exam(String title, Candidate candidate) {
        this.title = title;
        this.candidate = candidate;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void startExam() {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        int obtainedScore = 0;

        System.out.println("=== " + title + " басталуда ===");
        System.out.println(candidate.toString());

        for (Question q : questions) {
            q.display();
            System.out.print("Сіздің жауабыңыз (мәтін немесе нұсқа): ");
            String userAnswer = scanner.nextLine();

            totalScore += q.getScore();
            if (q.checkAnswer(userAnswer)) {
                System.out.println("Дұрыс! + " + q.getScore() + " балл.");
                obtainedScore += q.getScore();
            } else {
                System.out.println("Қате. Дұрыс жауап: " + q.getCorrectAnswer());
            }
        }

        System.out.println("\n--- Нәтиже ---");
        System.out.println("Кандидат: " + candidate.getName());
        System.out.println("Жалпы балл: " + obtainedScore + " / " + totalScore);
    }
}