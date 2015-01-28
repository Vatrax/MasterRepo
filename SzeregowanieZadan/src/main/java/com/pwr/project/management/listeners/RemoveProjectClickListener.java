package com.pwr.project.management.listeners;

import com.pwr.project.management.presenter.GanttPresenter;
import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;

/**
 * Created by krzaczek on 22.01.15.
 */
public class RemoveProjectClickListener implements Button.ClickListener {
	private final Container dataSource;
	private final ComboBox projects;
	private GanttPresenter ganttPresenter;

	public RemoveProjectClickListener(Container dataSource, ComboBox projects, GanttPresenter ganttPresenter) {
		this.dataSource = dataSource;
		this.projects = projects;
		this.ganttPresenter = ganttPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		if (projects.getValue() != null) {
			dataSource.removeItem(projects.getValue());
			ganttPresenter.updateGantt();
		}
		projects.setValue(null);
	}
}
