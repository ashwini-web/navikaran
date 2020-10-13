package com.capgemini.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/categories")
public class CanvasjsChartController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView springMVC(ModelMap modelMap) {
		ModelAndView model = new ModelAndView("chart");
		return model;
	}
}
