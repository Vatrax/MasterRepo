package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Team;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.ui.TeamManagementPresenter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class RemoveTeamClickListener implements Button.ClickListener {
	private List<Team> dataSource;

	private ComboBox teams;
	private GanttPresenter ganttPresenter;
	private TeamManagementPresenter teamManagementPresenter;

	public RemoveTeamClickListener(List<Team> dataSource, GanttPresenter ganttPresenter,
			TeamManagementPresenter teamManagementPresenter) {
		this.dataSource = dataSource;
		this.ganttPresenter = ganttPresenter;
		this.teamManagementPresenter = teamManagementPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		if (teams.getValue() != null) {
			removeTeam();
			ganttPresenter.updateGantt();
			teamManagementPresenter.synchronizeView();
		}
		teams.setValue(null);
	}

	private void removeTeam() {
		String teamName = teams.getValue().toString();
		for(Team team : dataSource) {
			if(team.getName().equals(teamName)) {
				dataSource.remove(team);
				break;
			}
		}
	}

	public void setTeams(ComboBox teams) {
		this.teams = teams;
	}
}
