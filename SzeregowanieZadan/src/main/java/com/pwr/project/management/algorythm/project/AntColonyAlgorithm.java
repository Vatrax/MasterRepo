package com.pwr.project.management.algorythm.project;

import com.google.common.collect.ImmutableList;
import com.pwr.project.management.algorythm.analyser.ResultAnalyser;
import com.pwr.project.management.algorythm.team.BasicTeamAssigner;
import com.pwr.project.management.algorythm.team.TeamAssigner;
import com.pwr.project.management.exceptions.TooLowNumberOfProjectsException;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by krzaczek on 6/7/15.
 */
public class AntColonyAlgorithm implements Algorithm {

	private static final Logger LOG = Logger.getLogger("AntColonyAlgorithm");

	private TeamAssigner teamAssigner = new BasicTeamAssigner();
	private ResultAnalyser resultAnalyser = new ResultAnalyser();

	private int numberOfAnts;
	private int numberOfIterations;

	@Override
	public void serialize(List<Project> projects, List<Team> teams) {
		numberOfAnts = (int) Math.ceil(projects.size() / 10);
		numberOfIterations = projects.size();
		LOG.info("Init algorithm");
		if (projects.size() < 3) {
			throw new TooLowNumberOfProjectsException("To low number of projects");
		}
		numberOfIterations = projects.size();
		int maxProfit = Integer.MIN_VALUE;
		ImmutableList<Project> projectsBackup = ImmutableList.copyOf(projects);
		List<Project> bestResult = null;
		LOG.info("Start algorithm");
		for (int i = 0; i < numberOfIterations; i++) {
			LOG.info("Looking for phereomone");
			List<Project> bestAnt = bestResult;
			for (int j = 0; j < numberOfAnts; j++) {
				projects = findWay(bestAnt);
				teamAssigner.assignTasks(projects, teams);
				//analizeResults
				int profit = resultAnalyser.calculateProfit(teams, projectsBackup);
				if (profit >= maxProfit) {
					maxProfit = profit;
					bestResult = new ArrayList<>(projects);
				}
			}
		}
		LOG.info("Assign tasks for teams");
		teamAssigner.assignTasks(bestResult, teams);
		LOG.info("DONE");
	}

	private List<Project> findWay(List<Project> projects) {
		int projectToSwap = selectRandomProjectWithoutRoot(projects);
		int project2ToSwap = selectRandomProjectWithoutRoot(projects);
		while (projectToSwap == project2ToSwap) {
			project2ToSwap = selectRandomProjectWithoutRoot(projects);
		}
		Collections.swap(projects, projectToSwap, project2ToSwap);
		return projects;
	}

	private int selectRandomProjectWithoutRoot(List<Project> projects) {
		return (int) (Math.random() * projects.size());
	}

	public static void main(String[] args) {
		List<Project> projects = new ArrayList<>();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MONTH, 1);
		instance.add(Calendar.MONTH, 1);
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(10, Type.BRICKLAYER));
		tasks.add(new Task(7, Type.ELECTRICIAN));
		tasks.add(new Task(10, Type.PLUMBER));
		tasks.add(new Task(21, Type.RENOVATOR));
		for (int i = 0; i < 3; i++) {
			projects.add(new Project("KrzaQ's Villa" + i, tasks, i * 1000000, instance.getTime(), i * 200, i * 400));
		}
		List<Team> teams = new ArrayList<>();
		teams.add(new Team("MainBrickLayer", Type.BRICKLAYER, 200));
		teams.add(new Team("MainElectrician", Type.ELECTRICIAN, 300));
		teams.add(new Team("MainPlumber", Type.PLUMBER, 400));
		teams.add(new Team("MainRenovator", Type.RENOVATOR, 350));
		teams.add(new Team("SecondaryRenovator", Type.RENOVATOR, 400));
		long start = new Date().getTime();
		new TabuSearchAlgorithm().serialize(projects, teams);
		long end = new Date().getTime();
		System.out.println(end - start + "ms");
		System.out.println(new ResultAnalyser().calculateProfit(teams, projects) + "$");
	}
}
