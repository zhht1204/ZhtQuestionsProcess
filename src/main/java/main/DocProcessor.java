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

		Pattern questionLinePattern = Pattern.compile("\\d{1,3}．(.+)（(\\w){1,4}）(.*)", Pattern.DOTALL);
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
				line.replaceAll("\\t", " ");
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
						selection.append(matcher.group().substring(1) + "|");
					}
					line = br.readLine();
					if ((matcher = selectionPattern.matcher(line)).find()) {
						selection.append(matcher.group().substring(1) + "|");
					}
					line = br.readLine();
					if ((matcher = selectionPattern.matcher(line)).find()) {
						selection.append(matcher.group().substring(1) + "|");
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

		Pattern questionLinePattern = Pattern.compile("\\d{1,3}．(.+)（\\W）(.*)", Pattern.DOTALL);
		Pattern idPattern = Pattern.compile("\\d{1,3}");
		Pattern questionPattern = Pattern.compile("．([^（]+)");
		Pattern answerPattern = Pattern.compile("（\\W）");
		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);
			String line;
			JudgeQuestionsEntity temp;
			String answer;
			Matcher matcher;
			for (line = br.readLine(); line != null; line = br.readLine()) {
				line.replaceAll("\\t", " ");
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

	public void formatListToSv(List list, File outputFile, String separator) {
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			String selectionsString;
			String[] selections;
			for (Object o : list) {
				if (o instanceof SelectQuestionsEntity) {
					bw.write(((SelectQuestionsEntity) o).getId() + "");
					bw.write(separator);
					bw.write(((SelectQuestionsEntity) o).getQuestion());
					bw.write(separator);
					selectionsString = ((SelectQuestionsEntity) o).getSelections();
					selections = selectionsString.split("\\|");
					for (String s : selections) {
						bw.write(s);
						bw.write(separator);
					}
					bw.write(((SelectQuestionsEntity) o).getAnswer());
					bw.write(separator);
					bw.write(((SelectQuestionsEntity) o).getType() + "");
				} else if (o instanceof JudgeQuestionsEntity) {
					bw.write(((JudgeQuestionsEntity) o).getId() + "");
					bw.write(separator);
					bw.write(((JudgeQuestionsEntity) o).getQuestion());
					bw.write(separator);
					bw.write(((JudgeQuestionsEntity) o).getAnswer() + "");
				} else {
					logger.error("entity certification error");
				}
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void formatListToSv(List list, String separator) {
		formatListToSv(list, new File("output_files/output.txt"), separator);
	}

	public void formatListToSv(List list, File outputFile) {
		formatListToSv(list, outputFile, "\t");
	}

	public void formatListToSv(List list) {
		formatListToSv(list, new File("output_files/output.txt"), "\t");
	}
}
