package com.pwr.project.management.model;

import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class Project {

	private String name;
	private List<Task> tasks;
	private final int price;
	private final Date expectedEndDate;
	private final int bonus;
	private final int punishment;

	public Project(String name, List<Task> tasks, int price, Date expectedEndDate, int bonus, int punishment) {
		this.name = name;
		this.tasks = tasks;
		this.price = price;
		this.expectedEndDate = expectedEndDate;
		this.bonus = bonus;
		this.punishment = punishment;
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

	public int getPrice() {
		return price;
	}

	public Date getExpectedEndDate() {
		return expectedEndDate;
	}

	public int getBonus() {
		return bonus;
	}

	public int getPunishment() {
		return punishment;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Project)) {
			return false;
		}
		if (obj == this) {
			return  true;
		}
		if (((Project) obj).getName().equals(name)) {
			return true;
		}
		else return false;
	}
}
