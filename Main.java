import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Аты-жөніңіз: ");
        String name = sc.nextLine();

        Candidate candidate = new Candidate(name);
        QuestionRepository repo = new DatabaseManager();

        Exam exam = new Exam("Java Endterm", candidate, repo);

        try {
            exam.loadQuestions();


            System.out.println("Сұрақтардың JSON құрылымы (Debug):");

            for (Question q : repo.getAllQuestions()) {
                if (q instanceof MultipleChoiceQuestion) {
                    System.out.println(((MultipleChoiceQuestion) q).toJson().toString());
                }
            }
            System.out.println("------------------------------------------\n");


            exam.start();

        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}