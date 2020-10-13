package com.capgemini.navikaran;

import java.util.Map;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.capgemini.utils.CommonUtilities;
import com.capgemini.utils.ConvertToJSONFormat;
import com.capgemini.utils.ReadAndWriteToExcel;

import opennlp.tools.doccat.DoccatModel;

@Component
public class ReviewCommentsCategorizer {

	static JSONArray json = null;
	private static final String READ_FILE_NAME = "C:\\openNLP\\ReviewCommentsTestData.xlsx";
	private static final String WRITE_FILE_NAME = "C:\\openNLP\\WriteDataSet.xlsx";
	private static Object[][] outDataType = new Object[260][260];
	private static final ConvertToJSONFormat convertToJSONFormat = new ConvertToJSONFormat();

	public static void main(String[] args) throws Exception {

		// createOutputDataFile();
		getJSONConversion();

	}

	/**
	 * @throws Exception
	 */
	public static void createOutputDataFile() throws Exception {
		Object[][] datatypes = ReadAndWriteToExcel.getDataFromFile(READ_FILE_NAME);

		int rowNum = 0;
		DoccatModel model = CategorizeDocuments.prepareModel();
		String category = null;
		for (Object[] objects : datatypes) {
			int colNum = 0;
			for (Object object : objects) {
				String value = (String) object;
				if (value != null) {
					String sampleDocument = value.toLowerCase();
					sampleDocument = CommonUtilities.removeSpecialCharsFromString(sampleDocument);
					sampleDocument = CommonUtilities.removeStopWordsFromString(sampleDocument);
					category = CategorizeDocuments.getCaregories(model, sampleDocument);
					if (colNum == 0)
						outDataType[rowNum][colNum] = value;
					colNum++;
				} else {
					break;
				}
			}
			outDataType[rowNum][colNum] = category;
			rowNum++;
		}

		ReadAndWriteToExcel.setDataToFile(WRITE_FILE_NAME, outDataType);
	}

	public static  Map<String, Integer> getJSONConversion() {
		try {
			createOutputDataFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertToJSONFormat.convertDataToJSON(outDataType);
	}

}