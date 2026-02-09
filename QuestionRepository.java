import java.util.List;

public interface QuestionRepository {
    List<Question> getAllQuestions() throws DatabaseException;
    void saveUserResult(String name, int score) throws DatabaseException;
}