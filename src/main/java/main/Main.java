package main;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) {
		DocProcessor dp = new DocProcessor();
		try {
			File singleSelectionFile = new File(java.net.URLDecoder.decode(Main.class.getClassLoader().getResource("单选题"
					+ ".txt").getFile(), "utf-8"));
			File multiSelectionFile = new File(java.net.URLDecoder.decode(Main.class.getClassLoader().getResource("多选题"
					+ ".txt").getFile(), "utf-8"));
			File judgeFile = new File(java.net.URLDecoder.decode(Main.class.getClassLoader().getResource("判断题"
					+ ".txt").getFile(), "utf-8"));
			File singleSelectionOutFile = new File("output_files/single_selection.tsv");
			File multiSelectionOutFile = new File("output_files/multi_selection.tsv");
			File judgeOutFile = new File("output_files/judge.tsv");
			dp.formatListToSv(dp.processSelectQuestions(singleSelectionFile), singleSelectionOutFile);
			dp.formatListToSv(dp.processSelectQuestions(multiSelectionFile), multiSelectionOutFile);
			dp.formatListToSv(dp.processJudgeQuestions(judgeFile), judgeOutFile);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
