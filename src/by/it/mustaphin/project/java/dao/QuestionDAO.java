package by.it.mustaphin.project.java.dao;

import by.it.mustaphin.project.java.bean.Question;
import by.it.mustaphin.project.java.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO extends AbstactDAO implements InterfaceDAO<Question> {

    private static QuestionDAO questionDAO = null;

    public static QuestionDAO getQuestionDAO() {
        if (null == questionDAO) {
            synchronized (Question.class) {
                if (null == questionDAO) {
                    questionDAO = new QuestionDAO();
                }
            }
        }
        return questionDAO;
    }

    @Override
    public boolean create(Question question) throws SQLException {
        String theme = question.getTheme();
        question.setId_question(executeUpdate("INSERT INTO questions (theme) VALUES ('" + theme + "');"));
        return (0 < question.getId_question());
    }

    @Override
    public boolean update(Question question) throws SQLException {
        return (1 == executeUpdate("UPDATE qustions SET theme='" + question.getTheme() + " WHERE id_question='" + question.getId_question() + "';"));
    }

    @Override
    public boolean delete(Question question) throws SQLException {
        return (0 < executeUpdate("DELETE FROM questions WHERE id_question='" + question.getId_question() + "';"));
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return (0 < executeUpdate("DELETE FROM questions WHERE id_question='" + id + "';"));
    }

    @Override
    public Question read(Question question) throws SQLException {
        try (Connection con = ConnectionCreator.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM questions WHERE id_question='" + question.getId_question() + "';");
            if (rs.next()) {
                question = new Question(rs.getInt("id_question"), rs.getString("theme"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return question;
    }

    @Override
    public Question read(int id) throws SQLException {
        Question question = null;
        try (Connection con = ConnectionCreator.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM questions WHERE id_question='" + id + "';");
            if (rs.next()) {
                question = new Question(rs.getInt("id_question"), rs.getString("theme"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return question;
    }

    @Override
    public List<Question> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Question> getAll(String condition) throws SQLException {
        List<Question> questions = new ArrayList<>();
        try (Connection con = ConnectionCreator.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM questions" + condition + ";");
            while (rs.next()) {
                Question question = new Question(rs.getInt("id_question"), rs.getString("theme"));
                questions.add(question);
            }
        } catch (SQLException e) {
            throw e;
        }
        return questions;
    }
}
