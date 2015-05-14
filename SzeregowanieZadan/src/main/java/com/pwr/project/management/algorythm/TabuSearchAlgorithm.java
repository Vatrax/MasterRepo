package com.pwr.project.management.algorythm;

import com.google.common.collect.ImmutableList;
import com.pwr.project.management.algorythm.analyser.AlgorithmAnalyser;
import com.pwr.project.management.algorythm.analyser.ResultAnalyser;
import com.pwr.project.management.exceptions.CannotCalculateException;
import com.pwr.project.management.model.*;

import java.util.*;

/**
 * Created by krzaczek on 25.04.15.
 */
public class TabuSearchAlgorithm implements Algorithm {

	@Override
	public void serialize(List<Project> projects, List<Team> teams) {
		ResultAnalyser resultAnalyser = new ResultAnalyser();
		int numberOfIterations = projects.size();
		int tabuSize = projects.size();
		int maxProfit = Integer.MIN_VALUE;
		ImmutableList<Project> projectsBackup = ImmutableList.copyOf(projects);
		List<Project> bestResult = null;
		for (int i = 0; i < numberOfIterations; i++) {
			int root = i % projectsBackup.size();
			Set<Integer[]> tabus = new HashSet<>();
			for (int j = 0; j < tabuSize; j++) {
				projects = new ArrayList<>(projectsBackup);
				Integer[] tabu = new Integer[projects.size() - 1];
				//order projects
				List<Project> projectsResult = new LinkedList<>();
				projectsResult.add(projects.remove(root));
				while (!projects.isEmpty()) {
					int projectNumber = ((int) (Math.random() * projects.size()));
					projectsResult.add(projects.remove(projectNumber));
					tabu[projects.size()] = projectNumber;
				}
				if (tabus.contains(tabu)) {
					continue;
				}
				tabus.add(tabu);
				//assign tasks
				assignTasks(projectsResult, teams);
				//analizeResults
				int profit = resultAnalyser.calculateProfit(teams, projectsBackup);
				if (profit > maxProfit) {
					maxProfit = profit;
					bestResult = new ArrayList<>(projectsResult);
				}
			}
		}
		assignTasks(bestResult, teams);
	}

	private void assignTasks(List<Project> projects, List<Team> teams) {
		clearTeamsTasks(teams);
		for (Project project : projects) {
			Collections.sort(project.getTasks());
			Date prevTaskEnds = new Date();
			for (Task task : project.getTasks()) {
				Team bestTeam = getMostSuitableTeam(teams, task);
				Date start = bestTeam.getEnd().before(prevTaskEnds) ? prevTaskEnds : bestTeam.getEnd();
				Date cMax = calculateEnd(start, task.getDuration());
				AssignedTask assignedTask = new AssignedTask(start, cMax, project.getName());
				bestTeam.getTasks().add(assignedTask);
				bestTeam.setEnd(cMax);
				prevTaskEnds = cMax;
			}
		}
	}

	private void clearTeamsTasks(List<Team> teams) {
		for (Team team : teams) {
			team.setTasks(new ArrayList<AssignedTask>());
			team.setEnd(new Date());
		}
	}

	private Date calculateEnd(Date start, int duration) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, duration);
		return calendar.getTime();

	}

	private Team getMostSuitableTeam(List<Team> teams, Task task) {
		Team bestTeam = null;
		for (Team team : teams) {
			if (team.getType().equals(task.getType())) {
				bestTeam = getFreeTeam(bestTeam, team);
			}
		}
		if (bestTeam == null) {
			throw new CannotCalculateException();
		}
		return bestTeam;
	}

	private Team getFreeTeam(Team bestTeam, Team team) {
		if (bestTeam == null) {
			bestTeam = team;
		} else if (team.getEnd().before(bestTeam.getEnd())) {
			bestTeam = team;
		}
		return bestTeam;
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
		for (int i = 0; i < 10; i++) {
			projects.add(new Project("KrzaQ's Villa" + i, tasks, i * 1000000, instance.getTime(), i * 200, i * 400));
		}
		List<Team> teams = new ArrayList<>();
		teams.add(new Team("MainBrickLayer", Type.BRICKLAYER, 200));
		teams.add(new Team("MainElectrician", Type.ELECTRICIAN, 300));
		teams.add(new Team("MainPlumber", Type.PLUMBER, 400));
		teams.add(new Team("MainRenovator", Type.RENOVATOR, 350));
		teams.add(new Team("SecondaryRenovator", Type.RENOVATOR, 400));
		long start = new Date().getTime();
		new BasicFlowAlgorithm().serialize(projects, teams);
		long end = new Date().getTime();
		System.out.println(end - start + "ms");
	}
}
