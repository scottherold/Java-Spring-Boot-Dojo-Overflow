package com.sherold.dojooverflow.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity // designates DB entity
@Table(name="answers") // Maps entity to DB table
public class Answer {
	@Id // designates id field
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Long id;
	@NotBlank(message="The answer field cannot be blank!")
	private String answer;
	@Column(updatable=false) // immutable after instantiation
	private Date createdAt;
	private Date updatedAt;
	
	// <----- Relationships ----->
	// 1:n w/ Question
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY) // Relationship established manually
	@JoinColumn(name="question_id") // PK
	private Question question;

	// <----- Constructors ----->
	public Answer() {
	}

	public Answer(String answer) {
		this.answer = answer;
	}

	public Answer(String answer, Question question) {
		this.answer = answer;
		this.question = question;
	}

	// <----- Getters/Setters ----->
	// answer
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	// question
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	// Getters only
	// id
	public Long getId() {
		return id;
	}

	// createdAt
	public Date getCreatedAt() {
		return createdAt;
	}

	// updatedAt
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	// <----- Methods ----->
	// Sets createdAt
	@PrePersist // run at time of instantiation
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	// Sets updatedAt
	@PreUpdate // run at time of update
	protected void onUpdate() {
		this.updatedAt = new Date();
	}	
}
