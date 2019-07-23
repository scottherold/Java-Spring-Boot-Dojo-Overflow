package com.sherold.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sherold.dojooverflow.models.Question;
import com.sherold.dojooverflow.models.Tag;
import com.sherold.dojooverflow.services.AppService;

@Controller
@RequestMapping("tags")
public class TagController {
	// <----- Attributes ----->
	// dependency injection
	private AppService appService;

	// <----- Constructors ----->
	public TagController(AppService appService) {
		this.appService = appService;
	}
	
	// <----- Methods ----->
	// Show (path)
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		// Queries DB for tag
		Tag tag = appService.findTag(id);
		
		model.addAttribute("subject", tag.getSubject());
		model.addAttribute("questions", tag.getQuestions());
		return "/tags/show.jsp";
	}
	
	// Show (search)
	@RequestMapping(value="q", method = RequestMethod.GET)
	public String search(@RequestParam(value="subject") String subject, Model model) {
		// Queries DB for tags
		List<Tag> tags = appService.findTagBySubContaining(subject);
		
		// Creates question list from tags
		List<Question> questions = new ArrayList<Question>(); 
		for (Tag tag : tags) {
			questions.addAll(tag.getQuestions());
		}
		
		model.addAttribute("subject", subject);
		model.addAttribute("questions", questions);
		return "/tags/show.jsp";
	}
}
