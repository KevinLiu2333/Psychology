package com.wonders.smrz.entity;

public class Question {
	
	 private String questionId;

	 private String question;

	 
	public Question(String questionId, String question) {
		super();
		this.questionId = questionId;
		this.question = question;
	}


	public String getQuestionId() {
		return questionId;
	}


	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	 
	 

}
