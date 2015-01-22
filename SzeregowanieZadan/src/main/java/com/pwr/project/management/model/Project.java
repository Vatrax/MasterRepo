package com.pwr.project.management.model;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class Project {

	private String name;
	private List<Task> tasks;

	public Project(String name, List<Task> tasks) {
		this.name = name;
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
