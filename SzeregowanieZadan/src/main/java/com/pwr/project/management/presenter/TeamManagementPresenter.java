package com.pwr.project.management.presenter;

import com.pwr.project.management.listeners.CreateTeamListener;
import com.pwr.project.management.listeners.RemoveTeamClickListener;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.ui.partial.TeamManagementLayout;
import com.vaadin.ui.Layout;

import java.util.List;

/**
 * Created by krzaczek on 16.03.15.
 */
public class TeamManagementPresenter {
	private final List<Team> teams;
	private final GanttPresenter ganttPresenter;
	private TeamManagementLayout managementLayout;

	public TeamManagementPresenter(List<Team> teams, GanttPresenter ganttPresenter) {
		this.teams = teams;
		this.ganttPresenter = ganttPresenter;
	}


	public Layout createView() {
		managementLayout = new TeamManagementLayout(teams, new CreateTeamListener(teams, ganttPresenter, this), new RemoveTeamClickListener(teams, ganttPresenter, this));
		return managementLayout;
	}

	public void synchronizeView() {
		managementLayout.synchronizeTeams(teams);
	}

	public String getTeamName() {
		return managementLayout.getTeamName();
	}

	public void clearTeamName() {
		managementLayout.setTeamName("");
	}

	public Type getType() {
		return managementLayout.getType();
	}
}
