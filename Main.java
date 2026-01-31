import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Атыңызды енгізіңіз: ");
        String name = scanner.nextLine();


        Candidate student = new Candidate(name);
        Exam javaExam = new Exam("Java Core Емтиханы", student);


        DatabaseManager db = new DatabaseManager();
        List<Question> dbQuestions = db.loadQuestions();


        if (dbQuestions == null || dbQuestions.isEmpty()) {
            System.out.println("Қате: Базадан сұрақтар табылмады!");
            return;
        }


        for (Question q : dbQuestions) {
            javaExam.addQuestion(q);
        }


        javaExam.startExam();

        scanner.close();
    }
}