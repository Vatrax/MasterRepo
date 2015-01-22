package com.pwr.project.management.model;

/**
 * Created by krzaczek on 22.01.15.
 */
public enum Type {
	BRICKLAYER(1), ELECTRICIAN(2), PLUMBER(3), RENOVATIONER(4);

	private int priority;

	Type(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
}
