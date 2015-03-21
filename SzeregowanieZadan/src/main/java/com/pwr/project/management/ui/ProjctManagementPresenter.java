package com.pwr.project.management.ui;

import com.pwr.project.management.listeners.CreateProjectListener;
import com.pwr.project.management.listeners.RemoveProjectClickListener;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.ui.partial.ProjectManagementLayout;
import com.vaadin.ui.Layout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzaczek on 16.03.15.
 */
public class ProjctManagementPresenter {
	private final List<Project> projects;
	private final GanttPresenter ganttPresenter;
	private ProjectManagementLayout layout;

	public ProjctManagementPresenter(List<Project> projects, GanttPresenter ganttPresenter) {
		this.projects = projects;
		this.ganttPresenter = ganttPresenter;
	}

	public Layout createLayout() {
		layout = new ProjectManagementLayout(projects,
				new RemoveProjectClickListener(this, projects, ganttPresenter),
				new CreateProjectListener(this, projects, ganttPresenter));
		return layout;
	}

	public String getComboboxValue() {
		Object comboboxValue = layout.getComboboxValue();
		return comboboxValue != null ? comboboxValue.toString() : null;
	}

	public void synchronizeView() {
		layout.synchronizeProjects(projects);
	}

	public void clearProjectSelection() {
		layout.setComboboxValue(null);
	}

	public String getProjectName() {
		return layout.getPojectName();
	}

	public void clearProjectName() {
		layout.setProjectName("");
	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<>();
		if (layout.getBrickLayingValues() != 0) {
			tasks.add(new Task(layout.getBrickLayingValues(), Type.BRICKLAYER));
		}
		if (layout.getElectricalValues() != 0) {
			tasks.add(new Task(layout.getElectricalValues(), Type.ELECTRICIAN));
		}
		if (layout.getPlumbingValues() != 0) {
			tasks.add(new Task(layout.getPlumbingValues(), Type.PLUMBER));
		}
		if (layout.getRenovateValues()  != 0) {
			tasks.add(new Task(layout.getRenovateValues(), Type.RENOVATOR));
		}
		clearTasks();
		return tasks;
	}

	private void clearTasks() {
		layout.setBrickLayingValues(0);
		layout.setElectricalValues(0);
		layout.setPlumbingValues(0);
		layout.setRenovateValues(0);
	}
}
