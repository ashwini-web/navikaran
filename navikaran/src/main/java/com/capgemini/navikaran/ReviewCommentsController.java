package com.capgemini.navikaran;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/canvasjschart")
public class ReviewCommentsController {

	@RequestMapping(method = RequestMethod.GET)
	public String springMVC() {
		return "chart to see";
	}

}
