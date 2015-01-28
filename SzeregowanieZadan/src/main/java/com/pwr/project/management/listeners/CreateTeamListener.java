package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.GanttPresenter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;

/**
 * Created by krzaczek on 22.01.15.
 */
public class CreateTeamListener implements Button.ClickListener {

	private final BeanContainer<String, Team> dataSource;
	private final ComboBox typeSelector;
	private final TextField teamName;
	private GanttPresenter ganttPresenter;

	public CreateTeamListener(BeanContainer<String, Team> dataSource, ComboBox typeSelector, TextField teamName,
			GanttPresenter ganttPresenter) {
		this.dataSource = dataSource;
		this.typeSelector = typeSelector;
		this.teamName = teamName;
		this.ganttPresenter = ganttPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		if (teamName.getValue() != null && !teamName.getValue().isEmpty() && typeSelector.getValue() != null) {
			Team team = new Team(teamName.getValue(), (Type) typeSelector.getValue());
			teamName.setValue("");
			dataSource.addBean(team);
			ganttPresenter.updateGantt();
		}
	}
}
