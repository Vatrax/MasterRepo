package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.presenter.ProjctManagementPresenter;
import com.vaadin.ui.Button;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class RemoveProjectClickListener implements Button.ClickListener {
	private ProjctManagementPresenter presenter;
	private final List<Project> dataSource;
	private GanttPresenter ganttPresenter;

	public RemoveProjectClickListener(ProjctManagementPresenter presenter, List<Project> dataSource, GanttPresenter ganttPresenter) {
		this.presenter = presenter;
		this.dataSource = dataSource;
		this.ganttPresenter = ganttPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		if (presenter.getComboboxValue() != null) {
			removeProject();
			ganttPresenter.updateGantt();
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
