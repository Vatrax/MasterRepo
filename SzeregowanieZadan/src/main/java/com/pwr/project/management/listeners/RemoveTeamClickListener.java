package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Team;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;

/**
 * Created by krzaczek on 22.01.15.
 */
public class RemoveTeamClickListener implements Button.ClickListener {
	private final BeanContainer<String, Team> dataSource;
	private final ComboBox teams;

	public RemoveTeamClickListener(BeanContainer<String, Team> dataSource, ComboBox teams) {
		this.dataSource = dataSource;
		this.teams = teams;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		dataSource.removeItem(teams.getValue());
	}
}
