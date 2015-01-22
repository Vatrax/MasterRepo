package com.pwr.project.management.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by krzaczek on 22.01.15.
 */
public class Team implements Serializable{

	private String name;
	private Type type;
	private Map<Task, Integer> tasks;
	private int end = 0;

	public Team(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Map<Task, Integer> getTasks() {
		return tasks;
	}

	public void setTasks(Map<Task, Integer> tasks) {
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
