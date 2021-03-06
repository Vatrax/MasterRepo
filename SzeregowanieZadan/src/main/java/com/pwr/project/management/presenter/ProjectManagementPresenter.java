package com.pwr.project.management.presenter;

import com.pwr.project.management.listeners.CreateProjectListener;
import com.pwr.project.management.listeners.RemoveProjectClickListener;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.ui.partial.ProjectManagementLayout;
import com.vaadin.ui.Layout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 16.03.15.
 */
public class ProjectManagementPresenter {
	private final List<Project> projects;
	private final GanttPresenter ganttPresenter;
	private ProjectManagementLayout layout;
	private AlgorythmManagementPresenter algorythmManagementPresenter;

	public ProjectManagementPresenter(List<Project> projects, GanttPresenter ganttPresenter,
			AlgorythmManagementPresenter algorythmManagementPresenter) {
		this.projects = projects;
		this.ganttPresenter = ganttPresenter;
		this.algorythmManagementPresenter = algorythmManagementPresenter;
	}

	public Layout createLayout() {
		layout = new ProjectManagementLayout(projects,
				new RemoveProjectClickListener(this, projects, ganttPresenter, algorythmManagementPresenter),
				new CreateProjectListener(this, projects, ganttPresenter, algorythmManagementPresenter));
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
		return layout.getProjectName();
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
		if (layout.getRenovateValues() != 0) {
			tasks.add(new Task(layout.getRenovateValues(), Type.RENOVATOR));
		}
		return tasks;
	}

	public void clearTasks() {
		layout.setBrickLayingValues(0);
		layout.setElectricalValues(0);
		layout.setPlumbingValues(0);
		layout.setRenovateValues(0);
	}

	public int getPrice() {
		String price = layout.getPriceValue();
		return price == null ? 0 : Integer.valueOf(price);
	}

	public Date getExpectedEndDate() {
		Date expectedEndDateValue = layout.getExpectedEndDateValue();
		return expectedEndDateValue == null ? new Date(Long.MAX_VALUE) : expectedEndDateValue;
	}

	public int getBonus() {
		String bonus = layout.getBonusValue();
		return bonus == null? 0 : Integer.valueOf(bonus);
	}

	public int getPunishment() {
		String punishment = layout.getPunishmentValue();
		return punishment == null ? 0 : Integer.valueOf(punishment);
	}

	public void resetLayout() {
		clearProjectName();
		clearTasks();
		clearDate();
		setDefaultProfit();
		setDefaultBonus();
		setDefaultPunishment();
	}

	private void setDefaultPunishment() {
		layout.setPunishmentValue(500);
	}

	private void setDefaultBonus() {
		layout.setBonusValue(1000);
	}

	private void setDefaultProfit() {
		layout.setProfitValue(10000);
	}

	private void clearDate() {
		layout.setDateValue(null);
	}
}
