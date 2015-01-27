package com.pwr.project.management.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class Team implements Serializable{

	private String name;
	private Type type;
	private List<AssignedTask> tasks = new ArrayList<AssignedTask>();
	private long end = 0;

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

	public List<AssignedTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<AssignedTask> tasks) {
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

}
