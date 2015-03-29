package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.presenter.AlgorythmManagementPresenter;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.presenter.ProjectManagementPresenter;
import com.vaadin.ui.Button;

import java.util.List;

/**
 * Created by krzaczek on 28.01.15.
 */
public class CreateProjectListener implements Button.ClickListener {

	private final ProjectManagementPresenter projectManagementPresenter;
	private final List<Project> projects;
	private final GanttPresenter ganttPresenter;
	private final AlgorythmManagementPresenter algorythmManagementPresenter;

	public CreateProjectListener(ProjectManagementPresenter projectManagementPresenter, List<Project> projects,
			GanttPresenter ganttPresenter, AlgorythmManagementPresenter algorythmManagementPresenter) {
		this.projectManagementPresenter = projectManagementPresenter;
		this.projects = projects;
		this.ganttPresenter = ganttPresenter;
		this.algorythmManagementPresenter = algorythmManagementPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		String projectName = projectManagementPresenter.getProjectName();
		if (projectName != null && !projectName.isEmpty()) {
			List<Task> tasks = projectManagementPresenter.getTasks();
			if (!tasks.isEmpty()) {
				Project project = new Project(projectName, tasks, projectManagementPresenter.getPrice(),
						projectManagementPresenter.getExpectedEndDate(), projectManagementPresenter.getBonus(),
						projectManagementPresenter.getPunishment());
				projectManagementPresenter.resetLayout();
				projects.add(project);
				ganttPresenter.updateGantt();
				projectManagementPresenter.synchronizeView();
				algorythmManagementPresenter.updateAlgorythmResults(ganttPresenter.getAlgorithmAnalyser());

			}
		}
	}
}
