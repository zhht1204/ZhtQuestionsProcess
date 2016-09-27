package bean;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/9/28.
 */
@Entity
@Table(name = "judge_questions", schema = "java_sanji", catalog = "")
public class JudgeQuestionsEntity {
	private int id;
	private String question;
	private String answer;

	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "question")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Basic
	@Column(name = "answer")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		JudgeQuestionsEntity that = (JudgeQuestionsEntity) o;

		if (id != that.id) {
			return false;
		}
		if (question != null ? !question.equals(that.question) : that.question != null) {
			return false;
		}
		if (answer != null ? !answer.equals(that.answer) : that.answer != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (question != null ? question.hashCode() : 0);
		result = 31 * result + (answer != null ? answer.hashCode() : 0);
		return result;
	}
}
