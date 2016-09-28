package main;

import bean.JudgeQuestionsEntity;
import bean.SelectQuestionsEntity;
import org.slf4j.Logger;
import utils.LoggerUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocProcessor {
	private static Logger logger = LoggerUtil.getLogger();

	public List<SelectQuestionsEntity> processSelectQuestions(File inputFile) {
		List<SelectQuestionsEntity> resultList = new ArrayList<SelectQuestionsEntity>();

		Pattern questionLinePattern = Pattern.compile("\\d{1,3}．([^（]+)（(\\w){1,4}）", Pattern.DOTALL);
		Pattern idPattern = Pattern.compile("\\d{1,3}");
		Pattern questionPattern = Pattern.compile("．([^（]+)");
		Pattern answerPattern = Pattern.compile("（[A-Z]{1,4}）");
		Pattern selectionPattern = Pattern.compile("．([^\n]+)");
		StringBuilder selection;
		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			SelectQuestionsEntity temp;
			String answer;
			for (line = br.readLine(); line != null; line = br.readLine()) {
				if (questionLinePattern.matcher(line).matches()) {
					temp = new SelectQuestionsEntity();
					Matcher matcher;
					if ((matcher = idPattern.matcher(line)).find()) {
						temp.setId(Integer.parseInt(matcher.group()));
					}
					if ((matcher = questionPattern.matcher(line)).find()) {
						temp.setQuestion(matcher.group().substring(1));
					}
					if ((matcher = answerPattern.matcher(line)).find()) {
						answer = matcher.group();
						answer = answer.substring(1, answer.length() - 1);
						temp.setAnswer(answer);
						temp.setType(answer.length() > 1 ? (byte) 1 : (byte) 0);
					}
					selection = new StringBuilder();
					line = br.readLine();
					if ((matcher = selectionPattern.matcher(line)).find()) {
						selection.append(matcher.group().substring(1) + "；");
					}
					line = br.readLine();
					if ((matcher = selectionPattern.matcher(line)).find()) {
						selection.append(matcher.group().substring(1) + "；");
					}
					line = br.readLine();
					if ((matcher = selectionPattern.matcher(line)).find()) {
						selection.append(matcher.group().substring(1) + "；");
					}
					line = br.readLine();
					if ((matcher = selectionPattern.matcher(line)).find()) {
						selection.append(matcher.group().substring(1));
					}
					temp.setSelections(selection.toString());
					resultList.add(temp);
				}
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return resultList;
	}

	public List<JudgeQuestionsEntity> processJudgeQuestions(File inputFile) {
		List<JudgeQuestionsEntity> resultList = new ArrayList<JudgeQuestionsEntity>();

		Pattern questionLinePattern = Pattern.compile("\\d{1,3}．([^（]+)（\\W{1}）", Pattern.DOTALL);
		Pattern idPattern = Pattern.compile("\\d{1,3}");
		Pattern questionPattern = Pattern.compile("．([^（]+)");
		Pattern answerPattern = Pattern.compile("（\\W{1}）");
		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			JudgeQuestionsEntity temp;
			String answer;
			Matcher matcher;
			for (line = br.readLine(); line != null; line = br.readLine()) {
				if (questionLinePattern.matcher(line).matches()) {
					temp = new JudgeQuestionsEntity();
					if ((matcher = idPattern.matcher(line)).find()) {
						temp.setId(Integer.parseInt(matcher.group()));
					}
					if ((matcher = questionPattern.matcher(line)).find()) {
						temp.setQuestion(matcher.group().substring(1));
					}
					if ((matcher = answerPattern.matcher(line)).find()) {
						answer = matcher.group();
						answer = answer.substring(1, answer.length() - 1);
						temp.setAnswer(answer);
					}
					resultList.add(temp);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return resultList;
	}
}
