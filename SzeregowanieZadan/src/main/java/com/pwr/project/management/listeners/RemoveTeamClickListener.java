package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Team;
import com.pwr.project.management.presenter.AlgorythmManagementPresenter;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.presenter.TeamManagementPresenter;
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
	private AlgorythmManagementPresenter algorythmManagementPresenter;

	public RemoveTeamClickListener(List<Team> dataSource, GanttPresenter ganttPresenter,
			TeamManagementPresenter teamManagementPresenter, AlgorythmManagementPresenter algorythmManagementPresenter) {
		this.dataSource = dataSource;
		this.ganttPresenter = ganttPresenter;
		this.teamManagementPresenter = teamManagementPresenter;
		this.algorythmManagementPresenter = algorythmManagementPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		if (teams.getValue() != null) {
			removeTeam();
			ganttPresenter.updateGantt();
			teamManagementPresenter.synchronizeView();
			algorythmManagementPresenter.updateAlgorythmResults(ganttPresenter.getAlgorithmAnalyser());
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
