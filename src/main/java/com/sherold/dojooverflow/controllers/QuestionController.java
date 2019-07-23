package com.sherold.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sherold.dojooverflow.models.Answer;
import com.sherold.dojooverflow.models.Question;
import com.sherold.dojooverflow.models.QuestionTags;
import com.sherold.dojooverflow.models.Tag;
import com.sherold.dojooverflow.services.AppService;

@Controller
@RequestMapping("questions")
public class QuestionController {
	// <----- Attributes ----->
	// dependency injection
	private AppService appService;

	// <----- Constructors ----->
	public QuestionController(AppService appService) {
		this.appService = appService;
	}
	
	// <----- Methods ----->
	// GET routes
	// all
	@RequestMapping(value="", method = RequestMethod.GET)
	public String index(Model model) {
		// Query all questions, add to model
		model.addAttribute("questions", appService.allQuestions());
		
		return "/questions/index.jsp";
	}
	
	// new
	@RequestMapping(value="new", method = RequestMethod.GET)
	public String newQuestion(@ModelAttribute("questionTags") QuestionTags questionTags) {
		return "/questions/new.jsp";
	}
	
	// show
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, @ModelAttribute("answerData") Answer answer, Model model) {
		model.addAttribute("question", appService.findQuestion(id));
		return "/questions/show.jsp";		
	}
	
	// POST routes
	// new
	@RequestMapping(value="new", method = RequestMethod.POST)
	public String addNewQuestion(@Valid @ModelAttribute("questionTags") QuestionTags questionTags, BindingResult result) {		
		if (result.hasErrors()) {
			return "questions/new.jsp";
		} else {
			// Checks to see if tags have data
			if(!questionTags.getTags().equals("")) {
				// Tag variables for DB entry
				List<String> tagScrub = appService.processTags(questionTags.getTags());			
				
				// Creates tags			
				List<Tag> newTags = new ArrayList<Tag>();
				for (String tag : tagScrub) {
					if(!"".equals(tag)) {
						Tag newTag = new Tag(tag);
						// checks to see if tag exists, if not save as new tag
						if (appService.findTagBySub(tag) == null) {
							appService.saveTag(newTag);
							newTags.add(newTag);
						} else {
							newTags.add(appService.findTagBySub(tag));
						}
					}
				}
				// Instantiate question and with form data/tag list 
				appService.saveQuestion(new Question(questionTags.getQuestion(), newTags));
			} else {
				// Blank tag entry
				appService.saveQuestion(new Question(questionTags.getQuestion()));
			}

			return "redirect:/questions";			
		}
		
	}
	
}
