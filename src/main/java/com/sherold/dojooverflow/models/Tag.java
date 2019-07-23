package com.sherold.dojooverflow.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity // designates DB entity
@Table(name="tags") // Maps entity to DB table
public class Tag {
	@Id // designates id field
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Long id;
	private String subject;	
	@Column(updatable=false) // immutable after instantiation
	private Date createdAt;
	private Date updatedAt;
	
	// <----- Relationships ----->
	// n:m w/ question
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY) // Relationship established manually
	// creates the middle table
	@JoinTable(
		name = "questions_tags", // sets middle table
		joinColumns = @JoinColumn(name = "tag_id"), // sets the column to map to this object 
		inverseJoinColumns = @JoinColumn(name = "question_id") // sets the column to map to linked object
	)
	private List<Question> questions;

	// <----- Constructors ----->
	public Tag() {
	}

	public Tag(String subject) {
		this.subject = subject;
	}

	public Tag(String subject, List<Question> questions) {
		this.subject = subject;
		this.questions = questions;
	}

	// <----- Getters/Setters ----->
	// subject
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	// questions
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
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
