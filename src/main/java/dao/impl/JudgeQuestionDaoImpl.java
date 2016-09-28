package dao.impl;

import bean.JudgeQuestionsEntity;
import dao.JudgeQuestionDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.DbUtil;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class JudgeQuestionDaoImpl implements JudgeQuestionDao {
	public void save(JudgeQuestionsEntity question) {
		Session session = DbUtil.getSession();
		try {
			Transaction transaction = session.beginTransaction();
			String sql = "insert into judge_questions(id,question,answer) values(:id, :question, :answer);";
			Query query = session.createSQLQuery(sql);
			query.setInteger("id", question.getId());
			query.setString("question", question.getQuestion());
			query.setString("answer", question.getAnswer());
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			LoggerUtil.getLogger().error(ex.getMessage());
		}
	}

	public List<JudgeQuestionsEntity> list() {
		List<JudgeQuestionsEntity> result = new ArrayList<JudgeQuestionsEntity>();
		Session session = DbUtil.getSession();
		try {
			String hql = "from JudgeQuestionsEntity";
			Query query = session.createQuery(hql);
			result = query.list();
		} catch (Exception ex) {
			LoggerUtil.getLogger().error(ex.getMessage());
		}
		return result;
	}
}
