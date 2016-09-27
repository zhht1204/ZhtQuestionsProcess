package test.dao;

import bean.JudgeQuestionsEntity;
import dao.impl.JudgeQuestionDaoImpl;
import org.junit.Before;
import org.junit.Test;
import test.AbstractTest;

import static org.junit.Assert.*;

public class JudgeQuestionDaoImplTest extends AbstractTest {
	private JudgeQuestionDaoImpl dao;

	@Before
	public void setUp() throws Exception {
		dao = new JudgeQuestionDaoImpl();
	}

	@Test
	public void save() throws Exception {
		JudgeQuestionsEntity question = new JudgeQuestionsEntity();
		question.setId(0);
		question.setQuestion("test question");
		question.setAnswer("A");
		dao.save(question);
	}

}