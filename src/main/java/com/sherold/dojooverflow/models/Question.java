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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity // designates DB entity
@Table(name="questions") // Maps entity to DB table
public class Question {
	@Id // designates id field
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Long id;
	private String question;
	@Column(updatable=false) // immutable after instantiation
	private Date createdAt;
	private Date updatedAt;
	
	// <----- Relationships ----->
	// n:1 w/ Answer
	@OneToMany(cascade=CascadeType.ALL, mappedBy="question", fetch = FetchType.LAZY) // Relationship established manually, on question object
	private List<Answer> answers;
	
	// n:m w/ tag
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY) // Relationship established manually
	// creates the middle table
	@JoinTable(
		name = "questions_tags", // sets middle table
		joinColumns = @JoinColumn(name = "question_id"), // sets the column to map to this object 
		inverseJoinColumns = @JoinColumn(name = "tag_id") // sets the column to map to linked object
	)
	private List<Tag> tags;

	// <----- Constructors ----->
	public Question() {
	}

	public Question(String question) {
		this.question = question;
	}
	
	public Question(String question, List<Tag> tags) {
		this.question = question;
		this.tags = tags;
	}	

	public Question(String question, List<Tag> tags, List<Answer> answers) {
		this.question = question;
		this.tags = tags;
		this.answers = answers;
	}

	// <----- Getters/Setters ----->
	// question
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	// answers
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	// tags
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
