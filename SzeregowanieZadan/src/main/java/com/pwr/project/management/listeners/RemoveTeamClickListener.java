package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Team;
import com.pwr.project.management.presenter.GanttPresenter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;

/**
 * Created by krzaczek on 22.01.15.
 */
public class RemoveTeamClickListener implements Button.ClickListener {
	private final BeanContainer<String, Team> dataSource;
	private final ComboBox teams;
	private GanttPresenter ganttPresenter;

	public RemoveTeamClickListener(BeanContainer<String, Team> dataSource, ComboBox teams,
			GanttPresenter ganttPresenter) {
		this.dataSource = dataSource;
		this.teams = teams;
		this.ganttPresenter = ganttPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		if (teams.getValue() != null) {
			dataSource.removeItem(teams.getValue());
			ganttPresenter.updateGantt();
		}
		teams.setValue(null);
	}
}
