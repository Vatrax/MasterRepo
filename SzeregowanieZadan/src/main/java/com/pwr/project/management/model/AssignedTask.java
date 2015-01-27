package com.pwr.project.management.model;

import java.util.Date;

/**
 * Created by krzaczek on 27.01.15.
 */
public class AssignedTask {

	private Date start;
	private Date end;
	private String projectName;

	public AssignedTask(Date start, Date end, String projectName) {
		this.start = start;
		this.end = end;
		this.projectName = projectName;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
