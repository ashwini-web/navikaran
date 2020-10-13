package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CanvasjsChartDao;

@Service
public class CanvasjsChartService {

	@Autowired
	private CanvasjsChartDao canvasjsChartDao;

	public void setCanvasjsChartDao(CanvasjsChartDao canvasjsChartDao) {
		this.canvasjsChartDao = canvasjsChartDao;
	}

	public String getCanvasjsChartData() {
		return canvasjsChartDao.getCanvasjsChartData();
	}

}
