package com.pwr.project.management.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class Team implements Serializable, Comparable {

	private String name;
	private Type type;
	private int cost;
	private List<AssignedTask> tasks = new ArrayList<AssignedTask>();
	private Date end = new Date();

	public Team(String name, Type type, int cost) {
		this.name = name;
		this.type = type;
		this.cost = cost;
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

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(Object o) {
		return ((Team) o).getType().getPriority() - this.getType().getPriority();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Team)) {
			return false;
		}
		if (obj == this) {
			return  true;
		}
		if (((Team) obj).getName().equals(name)) {
			return true;
		}
		else return false;
	}
}
