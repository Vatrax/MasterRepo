package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.ui.ProjctManagementPresenter;
import com.vaadin.ui.Button;

import java.util.List;

/**
 * Created by krzaczek on 28.01.15.
 */
public class CreateProjectListener implements Button.ClickListener {

	private final ProjctManagementPresenter projctManagementPresenter;
	private final List<Project> projects;
	private final GanttPresenter ganttPresenter;

	public CreateProjectListener(ProjctManagementPresenter projctManagementPresenter, List<Project> projects,
			GanttPresenter ganttPresenter) {
		this.projctManagementPresenter = projctManagementPresenter;
		this.projects = projects;
		this.ganttPresenter = ganttPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		String projectName = projctManagementPresenter.getProjectName();
		if (projectName != null && !projectName.isEmpty()) {
			List<Task> tasks = projctManagementPresenter.getTasks();
			if (!tasks.isEmpty()) {
				Project project = new Project(projectName, tasks);
				projctManagementPresenter.clearProjectName();
				projects.add(project);
				ganttPresenter.updateGantt();
				projctManagementPresenter.synchronizeView();
			}
		}
	}
}
