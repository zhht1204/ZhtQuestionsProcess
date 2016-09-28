package dao;

import bean.JudgeQuestionsEntity;

import java.util.List;

public interface JudgeQuestionDao {
	void save(JudgeQuestionsEntity question);

	List<JudgeQuestionsEntity> list();
}
