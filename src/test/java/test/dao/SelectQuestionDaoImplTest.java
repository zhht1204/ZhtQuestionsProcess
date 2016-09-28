package test.dao;

import bean.SelectQuestionsEntity;
import dao.impl.SelectQuestionDaoImpl;
import org.junit.Before;
import org.junit.Test;
import test.AbstractTest;

import java.util.List;

public class SelectQuestionDaoImplTest extends AbstractTest {
	SelectQuestionDaoImpl dao;

	@Before
	public void setUp() throws Exception {
		dao = new SelectQuestionDaoImpl();
	}

	@Test
	public void save() throws Exception {
		SelectQuestionsEntity question = new SelectQuestionsEntity();
		question.setId(0);
		question.setQuestion("test question");
		question.setSelections("A.haha；B.wawa");
		question.setAnswer("A");
		question.setType((byte)0);
		dao.save(question);
	}

	@Test
	public void list() {
		List<SelectQuestionsEntity> list = dao.list();
		for (SelectQuestionsEntity e : list) {
			logger.info(e.toString());
		}
	}

}