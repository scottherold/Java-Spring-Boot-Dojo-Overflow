package com.sherold.dojooverflow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {
	// GET routes
	// root
	@RequestMapping(value="", method = RequestMethod.GET)
	public String index() {
		return "/main/index.jsp";
	}
}
