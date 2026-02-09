import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Аты-жөніңіз: ");
        String name = sc.nextLine();

        Candidate candidate = new Candidate(name);
        QuestionRepository repo = new DatabaseManager(); // Инверсия зависимостей

        Exam exam = new Exam("Java Endterm", candidate, repo);

        try {
            exam.loadQuestions();
            exam.start();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}