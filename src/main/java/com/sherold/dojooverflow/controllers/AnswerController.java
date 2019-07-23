package com.sherold.dojooverflow.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sherold.dojooverflow.models.Answer;
import com.sherold.dojooverflow.services.AppService;

@Controller
@RequestMapping("answers")
public class AnswerController {
	// <----- Attributes ----->
	// dependency injection
	private AppService appService;

	// <----- Constructors ----->
	public AnswerController(AppService appService) {
		this.appService = appService;
	}
	
	// <----- Methods ----->
	// POST Routes
	@RequestMapping(value="new", method = RequestMethod.POST)
	public String addNewAnswer(@Valid @ModelAttribute("answerData") Answer answer, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("question", answer.getQuestion());
			return "questions/" + answer.getQuestion().getId();
		} else {
			appService.saveAnswer(answer);
			return "redirect:/questions/" + answer.getQuestion().getId(); 
		}
	}
	
}
