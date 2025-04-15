package com.guld.sciq.feedback.entity;

public enum FeedbackTargetType {
	QUESTION("질문"),
	DEBATE("토론");

	private final String description;

	FeedbackTargetType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
