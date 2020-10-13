package com.capgemini.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.capgemini.dao.ReviewComment;

public class CommonUtilities {
	private static final String path = "C:\\openNLP\\";

	/**
	 * Read the file and convert each word into lower case
	 * 
	 * @param fileName the file to be read
	 * @return file which contains all lower case data
	 */
	public static File getFileCharsConvertToLowerCase(String fileName) {
		try {
			String newFileName = fileName.substring(0, fileName.indexOf(".") - 1) + "_formated."
					+ fileName.substring(fileName.indexOf(".") + 1);
			File file2 = new File(newFileName);

			BufferedReader in = (new BufferedReader(new FileReader(fileName)));
			PrintWriter out = (new PrintWriter(new FileWriter(file2)));
			int ch;
			while ((ch = in.read()) != -1) {
				if (Character.isUpperCase(ch)) {
					ch = Character.toLowerCase(ch);// convert assign variable
				}
				out.write(ch);
			}

			in.close();
			out.close();

			return file2;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Read the file and remove all stop words for eg. a, any, this , that etc.
	 * 
	 * @param sampleFile file to be read
	 * @return file without stop words
	 */
	public static File removeStopWordsFromFile(File sampleFile) {

		String testText, stopWords = "";

		testText = readFileToString(sampleFile.getAbsolutePath());
		stopWords = readFileToString(path + "nlp_en_stop_words.txt");

		String[] stopWordsSet = stopWords.split("\n"); // new String[]{"a", "able", "about", "above", "according",
														// "accordingly", "across", "actually", "after", "afterwards",
														// "again", "against", "all"};
		String stopWordsPattern = String.join("|", stopWordsSet);
		Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(testText);
		testText = matcher.replaceAll("");
		writeToFile(testText, sampleFile);
		return sampleFile;

	}

	public static String removeStopWordsFromString(String sampleString) {

		String stopWords = "";

		stopWords = readFileToString(path + "nlp_en_stop_words.txt");

		String[] stopWordsSet = stopWords.split("\n"); // new String[]{"a", "able", "about", "above", "according",
														// "accordingly", "across", "actually", "after", "afterwards",
														// "again", "against", "all"};
		String stopWordsPattern = String.join("|", stopWordsSet);
		Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sampleString);
		sampleString = matcher.replaceAll("");

		return sampleString;

	}

	public static String readFileToString(String sampleFileName) {
		try {
			String testText = "";
			BufferedReader br = new BufferedReader(new FileReader(sampleFileName));

			int val;
			while ((val = br.read()) != -1) {
				testText += (char) val;
			}
			// System.out.println("read file as String : " + testText);
			br.close();
			return testText;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void writeToFile(String data, File sampleFile) {
		try {
			PrintWriter out = (new PrintWriter(new FileWriter(sampleFile)));
			out.write(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File removeSpecialCharsFromFile(File sampleFile) {

		String removedSpecialChars = StringUtils.stripAccents(readFileToString(sampleFile.getAbsolutePath()));

		String specialCharacters = "!#$%&'()*+,-/:;<=>?@[]^_`{|}";

		for (int i = 0; i < removedSpecialChars.length(); i++) {

			if (specialCharacters.contains(Character.toString(removedSpecialChars.charAt(i)))) {
				System.out.println(removedSpecialChars.charAt(i) + ": is a special character");
				removedSpecialChars = removedSpecialChars.replace(removedSpecialChars.charAt(i), ' ');
			}
		}
		// System.out.println("removed special chars "+removedSpecialChars);

		writeToFile(removedSpecialChars, sampleFile);
		return sampleFile;
	}

	public static String removeSpecialCharsFromString(String sampleString) {

		String specialCharacters = "!#$%&'()*+,-/:;<=>?@[]^_`{|}";

		for (int i = 0; i < sampleString.length(); i++) {

			if (specialCharacters.contains(Character.toString(sampleString.charAt(i)))) {
				System.out.println(sampleString.charAt(i) + ": is a special character");
				sampleString = sampleString.replace(sampleString.charAt(i), ' ');
			}
		}
		// System.out.println("removed special chars from String::"+sampleString);

		return sampleString;
	}

	public static List<ReviewComment> convertMapIntoJSonObject(final Map<String, Integer> dataMap) {
		List<ReviewComment> reviewCommentList = new ArrayList<ReviewComment>();
		for (Entry<String, Integer> reviewComment : dataMap.entrySet()) {
			if(!reviewComment.getKey().equals("null")) {
			reviewCommentList.add(new ReviewComment(reviewComment.getKey(), reviewComment.getValue()));
		}
		}
		return reviewCommentList;
	}
}
