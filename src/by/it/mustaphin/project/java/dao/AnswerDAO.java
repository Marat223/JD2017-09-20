package dao;

import bean.Answer;
import connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO extends AbstactDAO implements InterfaceDAO<Answer> {

    private static AnswerDAO answerDAO = null;

    public InterfaceDAO getInstanse() {
        if (null == answerDAO) {
            synchronized (AnswerDAO.class) {
                if (null == answerDAO) {
                    answerDAO = new AnswerDAO();
                }
            }
        }
        return answerDAO;
    }

    @Override
    public boolean create(Answer answer) throws SQLException {
        boolean correct = answer.isCorrect();
        int fk_question = answer.getFk_question();
        String text = answer.getText();
        int id = executeCreate("INSERT INTO answers (correct, fk_question, text) VALUES ('" + correct + "', '" + fk_question + "', '" + text + "');");
        if (0 < id) {
            answer.setId_answer(id);
        }
        return (0 < id);
    }

    @Override
    public boolean update(Answer answer) throws SQLException {
        return (1 == executeCreate("UPDATE answers SET correct='" + answer.isCorrect() + "', fk_question='" + answer.getFk_question() + "' WHERE id_answer='" + answer.getId_answer() + "';"));
    }

    @Override
    public boolean delete(Answer answer) throws SQLException {
        return (0 < executeCreate("DELETE FROM answers WHERE id_answer='" + answer.getId_answer() + "';"));
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return (0 < executeCreate("DELETE FROM answers WHERE id_answer='" + id + "';"));
    }

    @Override
    public Answer read(Answer answer) throws SQLException {
        try (Connection con = ConnectionCreator.getConnection();
                Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM answers WHERE id_answer='" + answer.getId_answer() + "';");
            if (rs.next()) {
                answer = new Answer(
                        rs.getBoolean("correct"),
                        rs.getInt("fk_question"),
                        rs.getString("text"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return answer;
    }

    @Override
    public Answer read(int id) throws SQLException {
        Answer answer = null;
        try (Connection con = ConnectionCreator.getConnection();
                Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM answers WHERE id_answer='" + id + "';");
            if (rs.next()) {
                answer = new Answer(
                        rs.getBoolean("correct"),
                        rs.getInt("fk_question"),
                        rs.getString("text"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return answer;
    }

    @Override
    public List<Answer> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Answer> getAll(String condition) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        try (Connection con = ConnectionCreator.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM answers" + condition + ";");
            while (rs.next()) {
                Answer answer = new Answer(
                        rs.getInt("id_answer"),
                        rs.getBoolean("correct"),
                        rs.getInt("fk_question"),
                        rs.getString("text"));
                answers.add(answer);
            }
        } catch (SQLException e) {
            throw e;
        }
        return answers;
    }
}
