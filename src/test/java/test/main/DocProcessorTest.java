package test.main;

import bean.JudgeQuestionsEntity;
import bean.SelectQuestionsEntity;
import main.DocProcessor;
import org.junit.Before;
import org.junit.Test;
import test.AbstractTest;

import java.io.File;
import java.util.List;

public class DocProcessorTest extends AbstractTest {
	DocProcessor dp;

	@Before
	public void setUp() throws Exception {
		dp = new DocProcessor();
	}

	@Test
	public void processSelectQuestions() throws Exception {
		File file = new File(java.net.URLDecoder.decode(DocProcessorTest.class.getClassLoader().getResource("单选题"
				+ ".txt").getFile(), "utf-8"));
		List<SelectQuestionsEntity> singleSelections = dp.processSelectQuestions(file);
		for (SelectQuestionsEntity e : singleSelections) {
			logger.info(e.toString());
		}
	}

	@Test
	public void processJudgeQuestions() throws Exception {
		File file = new File(java.net.URLDecoder.decode(DocProcessorTest.class.getClassLoader().getResource("判断题"
				+ ".txt").getFile(), "utf-8"));
		List<JudgeQuestionsEntity> judges = dp.processJudgeQuestions(file);
		for (JudgeQuestionsEntity e : judges) {
			logger.info(e.toString());
		}
	}

}