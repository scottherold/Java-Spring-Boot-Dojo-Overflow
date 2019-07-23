package com.sherold.dojooverflow.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherold.dojooverflow.models.Answer;
import com.sherold.dojooverflow.models.Question;
import com.sherold.dojooverflow.models.Tag;
import com.sherold.dojooverflow.repositories.AnswerRepository;
import com.sherold.dojooverflow.repositories.QuestionRepository;
import com.sherold.dojooverflow.repositories.TagRepository;

@Service
public class AppService {
	// <----- Attributes ----->
	// dependency injection
	private AnswerRepository answerRepo;
	private QuestionRepository questionRepo;
	private TagRepository tagRepo;
	
	// <----- Constructors ----->
	public AppService(AnswerRepository answerRepo, QuestionRepository questionRepo, TagRepository tagRepo) {
		this.answerRepo = answerRepo;
		this.questionRepo = questionRepo;
		this.tagRepo = tagRepo;
	}
	
	// <----- Methods ----->
	// Find All
	
	// answers
	public List<Answer> allAnswers() {
		return answerRepo.findAll();
	}
	
	// questions
	public List<Question> allQuestions() {
		return questionRepo.findAll();
	}
	
	// tags
	public List<Tag> allTags() {
		return tagRepo.findAll();
	}
	
	// Find by Id
	// answer
	public Answer findAnswer(Long id) {
		// Optional allows for null
		Optional<Answer> opAnswer = answerRepo.findById(id);
		if(opAnswer.isPresent()) {
			return opAnswer.get();
		} else {
			return null;
		}
	}
	
	// question
	public Question findQuestion(Long id) {
		// Optional allows for null
		Optional<Question> opQuestion = questionRepo.findById(id);
		if(opQuestion.isPresent()) {
			return opQuestion.get();
		} else {
			return null;
		}
	}
	
	// tag
	public Tag findTag(Long id) {
		// Optional allows for null
		Optional<Tag> opTag = tagRepo.findById(id);
		if(opTag.isPresent()) {
			return opTag.get();
		} else {
			return null;
		}
	}
	
	// Save
	// answer
	public Answer saveAnswer(Answer a) {
		return answerRepo.save(a);
	}
	
	// question
	public Question saveQuestion(Question q) {
		return questionRepo.save(q);
	}
	
	// tag
	public Tag saveTag(Tag t) {
		return tagRepo.save(t);
	}
	
	// find tag by subject
	public Tag findTagBySub(String sub) {
		// Optional allows for null
		Optional<Tag> opTag = tagRepo.findBySubject(sub);
		if(opTag.isPresent()) {
			return opTag.get();
		} else {
			return null;
		}
	}
	
	// find tags by subject containing
	public List<Tag> findTagBySubContaining(String sub) {
		return tagRepo.findBySubjectContaining(sub);
		
	}
	
	// Tag scrub
	public List<String> processTags(String tags) {
		// Splices string by commas
		List<String> tagList = new ArrayList<String>(Arrays.asList(tags.split("\\s*,\\s*")));
		return tagList;
	}
}
