package dao;

import bean.SelectQuestionsEntity;

import java.util.List;

public interface selectQuestionDao {
	void save(SelectQuestionsEntity question);

	List<SelectQuestionsEntity> list();
}
