package bean;

import javax.persistence.*;

@Entity
@Table(name = "select_questions", schema = "java_sanji", catalog = "")
public class SelectQuestionsEntity {
	private int id;
	private String question;
	private String selections;
	private String answer;
	private Byte type;

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
	@Column(name = "selections")
	public String getSelections() {
		return selections;
	}

	public void setSelections(String selections) {
		this.selections = selections;
	}

	@Basic
	@Column(name = "answer")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Basic
	@Column(name = "type")
	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SelectQuestionsEntity that = (SelectQuestionsEntity) o;

		if (id != that.id) {
			return false;
		}
		if (question != null ? !question.equals(that.question) : that.question != null) {
			return false;
		}
		if (selections != null ? !selections.equals(that.selections) : that.selections != null) {
			return false;
		}
		if (answer != null ? !answer.equals(that.answer) : that.answer != null) {
			return false;
		}
		if (type != null ? !type.equals(that.type) : that.type != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (question != null ? question.hashCode() : 0);
		result = 31 * result + (selections != null ? selections.hashCode() : 0);
		result = 31 * result + (answer != null ? answer.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "[SelectQuestionEntity: id=" + this.id + ";question=" + this.question + ";selections="
				+ this.selections + "" + ";answer=" + this.answer + ";type=" + (this.type == 0 ? "单选题" : "多选题")
				+ "]";
	}
}
