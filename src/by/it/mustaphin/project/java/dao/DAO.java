package by.it.mustaphin.project.java.dao;

public class DAO {

    private static DAO dao;
    public UserDAO user;
    public AnswerDAO answer;
    public QuestionDAO question;
    public RightDAO right;

    public static DAO getDAO() {
	if (null == dao) {
	    synchronized (DAO.class) {
		if (null == dao) {
		    dao = new DAO();
		    dao.user = new UserDAO();
		    dao.answer = new AnswerDAO();
		    dao.question = new QuestionDAO();
		    dao.right = new RightDAO();
		}
	    }
	}
	return dao;
    }
}
