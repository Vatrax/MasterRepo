package com.pwr.project.management.presenter;

import com.pwr.project.management.algorythm.project.Algorithm;
import com.pwr.project.management.algorythm.analyser.AlgorithmAnalyser;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.ui.partial.TeamView;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by krzaczek on 28.01.15.
 */
public class GanttPresenter {

	private static final Logger LOG = Logger.getLogger("GanttPresenter");

	private final AlgorithmAnalyser algorithmAnalyser;
	private TeamView view;
	private final List<Team> teams;
	private final List<Project> projects;
	private Algorithm algorithm;

	public GanttPresenter(TeamView view, List<Team> teams, List<Project> projects) {
		this.view = view;
		this.teams = teams;
		this.projects = projects;
		algorithmAnalyser = new AlgorithmAnalyser();
	}

	public void updateGantt() {
		try {
			algorithmAnalyser.runWithAnalyse(algorithm, projects, teams);
			view.drawSteps(teams);
		} catch (RuntimeException e) {
			view.removeSteps();
			LOG.info(e.getMessage());
		}
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public AlgorithmAnalyser getAlgorithmAnalyser() {
		return algorithmAnalyser;
	}
}
