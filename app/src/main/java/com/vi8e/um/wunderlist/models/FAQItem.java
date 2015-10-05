package com.vi8e.um.wunderlist.models;

public class FAQItem {
private long   id;
private String question, answer;

public
long getId () {
	return id;
}

public
void setId(long id) {
		this.id = id;
	}
	public
	String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public
	String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
