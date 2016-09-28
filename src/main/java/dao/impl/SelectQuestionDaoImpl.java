package dao.impl;

import bean.SelectQuestionsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.DbUtil;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectQuestionDaoImpl {
	public void save(SelectQuestionsEntity question) {
		Session session = DbUtil.getSession();
		try {
			Transaction transaction = session.beginTransaction();
			String sql = "insert into select_questions values(:id, :question, :selections, :answer, :type);";
			Query query = session.createSQLQuery(sql);
			query.setInteger("id", question.getId());
			query.setString("question", question.getQuestion());
			query.setString("selections", question.getSelections());
			query.setString("answer", question.getAnswer());
			query.setByte("type", question.getType());
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			LoggerUtil.getLogger().error(ex.getMessage());
		}
	}

	public List<SelectQuestionsEntity> list() {
		List<SelectQuestionsEntity> result = new ArrayList<SelectQuestionsEntity>();
		Session session = DbUtil.getSession();
		try {
			String hql = "from SelectQuestionsEntity";
			Query query = session.createQuery(hql);
			result = query.list();
		} catch (Exception ex) {
			LoggerUtil.getLogger().error(ex.getMessage());
		}
		return result;
	}
}
