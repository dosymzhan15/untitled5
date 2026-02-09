import java.sql.*;
import java.util.*;

public class DatabaseManager implements QuestionRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public List<Question> getAllQuestions() throws DatabaseException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String text = rs.getString("question_text");
                int score = rs.getInt("score");
                String correct = rs.getString("correct_answer");
                String optionsRaw = rs.getString("options");

                List<String> options = (optionsRaw != null)
                        ? Arrays.asList(optionsRaw.split(","))
                        : new ArrayList<>();

                questions.add(new MultipleChoiceQuestion(text, score, correct, options));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Деректер қорынан сұрақтарды жүктеу қатесі");
        }
        return questions;
    }

    @Override
    public void saveUserResult(String name, int score) throws DatabaseException {
        String sql = "INSERT INTO results (candidate_name, final_score) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Нәтижені сақтау кезінде қате кетті");
        }
    }
}