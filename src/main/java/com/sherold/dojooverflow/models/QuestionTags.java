package com.sherold.dojooverflow.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// For Binding/Forms
public class QuestionTags {
	// <----- Attributes ----->	
	@NotBlank(message="The question field cannot be blank!")
	private String question;
	@Pattern(regexp="^$|([a-z *]+(,[a-z *]+(,[a-z *])*)*){1}$", message="Tags must be lower case and separated by a comma. Only three tags can be applied per question.")
	private String tags;
	
	// <----- Constructors ----->
	public QuestionTags() {
	}

	public QuestionTags(String question) {
		this.question = question;
	}

	public QuestionTags(String question, String tags) {
		this.question = question;
		this.tags = tags;
	}

	// <----- Getters/Setters----->
	// question
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	// tags
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}	
	
}
