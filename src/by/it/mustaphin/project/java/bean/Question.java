package by.it.mustaphin.project.java.bean;

public class Question {
    private int id_question;
    private String theme;

    public Question() {
    }

    public Question(int id_question, String theme) {
        this.id_question = id_question;
        this.theme = theme;
    }

    public Question(String theme) {
        this.theme = theme;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (id_question != question.id_question) return false;
        return theme.equals(question.theme);
    }

    @Override
    public int hashCode() {
        int result = id_question;
        result = 31 * result + theme.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "theme='" + theme + '\'' +
                '}';
    }
}
