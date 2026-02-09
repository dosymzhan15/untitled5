public class ResultDto {
    private String name;
    private int score;

    // Пустой конструктор (нужен для Jackson/Javalin)
    public ResultDto() {}

    // Геттеры
    public String getName() { return name; }
    public int getScore() { return score; }
}