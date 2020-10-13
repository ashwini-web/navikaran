package com.capgemini.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reviews")
public class ReviewCommentCategoriserController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView springMVC(ModelMap modelMap) {
		ModelAndView model = new ModelAndView("reviews");
		return model;
	}
}
