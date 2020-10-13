package com.capgemini.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertToJSONFormat {

	private Map<String, Integer> tokenTypeMap = new HashMap<>();

	public Map<String, Integer> convertDataToJSON(Object[][] outDataType) {
		convertOutputDataToMap(outDataType);
		if (!tokenTypeMap.isEmpty()) {
			mapToJSON();
		}
		System.out.println(tokenTypeMap.toString());
		return tokenTypeMap;
	}

	private void mapToJSON() {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String jsonString = objectMapper.writeValueAsString(tokenTypeMap);
			System.out.println("JSON Data :: " + jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	private void convertOutputDataToMap(Object[][] outDataType) {
		for (Object[] mapping : outDataType) {
			if (tokenTypeMap.isEmpty() || !tokenTypeMap.containsKey(String.valueOf(mapping[1]))) {
				tokenTypeMap.put(String.valueOf(mapping[1]), 1);
			} else {
				tokenTypeMap.put(String.valueOf(mapping[1]), tokenTypeMap.get(String.valueOf(mapping[1])) + 1);
			}
		}
	}
}
