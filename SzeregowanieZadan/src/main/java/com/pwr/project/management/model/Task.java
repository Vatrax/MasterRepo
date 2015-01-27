package com.pwr.project.management.model;

import java.io.Serializable;

/**
 * Created by krzaczek on 22.01.15.
 */
public class Task implements Serializable, Comparable {

	private int duration;
	private Type type;

	public Task(int duration, Type type) {
		this.duration = duration;
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override public int compareTo(Object o) {
		return this.getType().getPriority() - ((Task) o).getType().getPriority();
	}
}
