package com.capgemini.dao;

import org.springframework.stereotype.Component;

import com.capgemini.model.CanvasjsChartData;

@Component
public class CanvasjsChartDao {

	public String getCanvasjsChartData() {
		return CanvasjsChartData.getCanvasjsDataList();
	}

}