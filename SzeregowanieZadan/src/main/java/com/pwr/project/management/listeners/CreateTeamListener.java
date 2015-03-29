package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.AlgorythmManagementPresenter;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.presenter.TeamManagementPresenter;
import com.vaadin.ui.Button;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class CreateTeamListener implements Button.ClickListener {

	private final List<Team> teams;

	private GanttPresenter ganttPresenter;
	private final TeamManagementPresenter teamManagementPresenter;
	private AlgorythmManagementPresenter algorythmManagementPresenter;

	public CreateTeamListener(List<Team> teams, GanttPresenter ganttPresenter,
			TeamManagementPresenter teamManagementPresenter, AlgorythmManagementPresenter algorythmManagementPresenter) {
		this.teams = teams;
		this.ganttPresenter = ganttPresenter;
		this.teamManagementPresenter = teamManagementPresenter;
		this.algorythmManagementPresenter = algorythmManagementPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		String teamName = teamManagementPresenter.getTeamName();
		Type type = teamManagementPresenter.getType();
		int price = teamManagementPresenter.getPrice();
		if (teamName != null && !teamName.isEmpty() && type != null) {
			Team team = new Team(teamName, type, price);
			teamManagementPresenter.clearTeamName();
			teamManagementPresenter.clearTeamPrice();
			teams.add(team);
			ganttPresenter.updateGantt();
			algorythmManagementPresenter.updateAlgorythmResults(ganttPresenter.getAlgorithmAnalyser());
			teamManagementPresenter.synchronizeView();
		}
	}

}
