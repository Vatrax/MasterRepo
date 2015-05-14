package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.AlgorythmManagementPresenter;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.presenter.ProjectManagementPresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 14.05.15.
 */
public class DataGeneratorListener implements ClickListener {

	private AlgorythmManagementPresenter presenter;
	private final List<Project> projects;
	private final GanttPresenter ganttPresenter;
	private ProjectManagementPresenter projectManagementPresenter;

	public DataGeneratorListener(AlgorythmManagementPresenter presenter, List<Project> projects,
			GanttPresenter ganttPresenter, ProjectManagementPresenter projectManagementPresenter) {
		this.presenter = presenter;
		this.projects = projects;
		this.ganttPresenter = ganttPresenter;
		this.projectManagementPresenter = projectManagementPresenter;
	}

	@Override public void buttonClick(ClickEvent event) {
		int numberOfProjects = presenter.getNumberOfProjects();
		presenter.setNumberOfProjects(1);
		populateProjects(numberOfProjects);
		projectManagementPresenter.synchronizeView();
		ganttPresenter.updateGantt();
	}

	private void populateProjects(int numberOfProjects) {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MONTH, 1);
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task((int) (Math.random() * 10), Type.BRICKLAYER));
		tasks.add(new Task((int) (Math.random() * 7), Type.ELECTRICIAN));
		tasks.add(new Task((int) (Math.random() * 10), Type.PLUMBER));
		tasks.add(new Task((int) (Math.random() * 20), Type.RENOVATOR));
		for (int i = 0; i < numberOfProjects; i++) {
			projects.add(new Project("sample"+ new Date().getTime() + i, tasks, i * 1000000, instance.getTime(), i * 200, i * 400));
			instance.add(Calendar.DATE, 14);
		}
	}
}
