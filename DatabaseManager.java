import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
    }

    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList();
        String query = "SELECT * FROM questions";

        String text;
        int score;
        String correct;
        List<String> options;
        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            for(; rs.next(); questions.add(new MultipleChoiceQuestion(text, score, correct, options))) {
                text = rs.getString("question_text");
                score = rs.getInt("score");
                correct = rs.getString("correct_answer");
                String optionsRaw = rs.getString("options");
                options = new ArrayList();
                if (optionsRaw != null) {
                    options = Arrays.asList(optionsRaw.split(","));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL қатесі: " + e.getMessage());
        }

        return questions;
    }

    public void saveResult(String candidateName, int score) {
        String sql = "INSERT INTO results (candidate_name, final_score) VALUES (?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, candidateName);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            System.out.println("Нәтиже базаға сәтті сақталды!");
        } catch (SQLException e) {
            System.err.println("Сақтау кезінде қате кетті: " + e.getMessage());
        }

    }
}
