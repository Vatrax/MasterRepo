package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.presenter.AlgorythmManagementPresenter;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.presenter.ProjectManagementPresenter;
import com.vaadin.ui.Button;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class RemoveProjectClickListener implements Button.ClickListener {
	private ProjectManagementPresenter presenter;
	private final List<Project> dataSource;
	private GanttPresenter ganttPresenter;
	private AlgorythmManagementPresenter algorythmManagementPresenter;

	public RemoveProjectClickListener(ProjectManagementPresenter presenter, List<Project> dataSource,
			GanttPresenter ganttPresenter, AlgorythmManagementPresenter algorythmManagementPresenter) {
		this.presenter = presenter;
		this.dataSource = dataSource;
		this.ganttPresenter = ganttPresenter;
		this.algorythmManagementPresenter = algorythmManagementPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		if (presenter.getComboboxValue() != null) {
			removeProject();
			ganttPresenter.updateGantt();
			algorythmManagementPresenter.updateAlgorythmResults(ganttPresenter.getAlgorithmAnalyser());
			presenter.synchronizeView();
		}
		presenter.clearProjectSelection();
	}

	private void removeProject() {
		String projectName = presenter.getComboboxValue();
		for(Project project : dataSource) {
			if(project.getName().equals(projectName)) {
				dataSource.remove(project);
				break;
			}
		}
	}
}
