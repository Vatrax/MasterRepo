package com.pwr.project.management.algorythm.project;

import com.pwr.project.management.algorythm.team.BasicTeamAssigner;
import com.pwr.project.management.algorythm.team.TeamAssigner;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class BasicFlowAlgorithm implements Algorithm {

	private TeamAssigner teamAssigner = new BasicTeamAssigner();

	@Override
	public void serialize(List<Project> projects, List<Team> teams) {
		teamAssigner.assignTasks(projects, teams);
	}

}
