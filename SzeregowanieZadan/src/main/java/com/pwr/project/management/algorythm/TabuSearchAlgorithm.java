package com.pwr.project.management.algorythm;

import com.google.common.collect.ImmutableList;
import com.pwr.project.management.algorythm.analyser.ResultAnalyser;
import com.pwr.project.management.exceptions.CannotCalculateException;
import com.pwr.project.management.exceptions.TooLowNumberOfProjectsException;
import com.pwr.project.management.model.*;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by krzaczek on 25.04.15.
 */
public class TabuSearchAlgorithm implements Algorithm {

	private static final Logger LOG = Logger.getLogger("TabuSearchAlgorithm");

	@Override
	public void serialize(List<Project> projects, List<Team> teams) {
		LOG.info("Init algorithm");
		if (projects.size() < 3) {
			throw new TooLowNumberOfProjectsException("To low number of projects");
		}
		ResultAnalyser resultAnalyser = new ResultAnalyser();
		int numberOfIterations = projects.size();
		int tabuSize = projects.size();
		int maxProfit = Integer.MIN_VALUE;
		ImmutableList<Project> projectsBackup = ImmutableList.copyOf(projects);
		List<Project> bestResult = null;
		LOG.info("Start algorithm");
		for (int i = 0; i < numberOfIterations; i++) {
			projects = createTabuNeighbourhood(projectsBackup, i);
			Set<ArrayList<String>> tabus = new HashSet<>();
			int newTabuSize;
			LOG.info("Searching Neighbours");
			int iterationCouter = 0;
			do {
				iterationCouter++;
				int tabusSize = tabus.size();
				//order projects
				projects = permuteProjects(projects, tabus);
				newTabuSize = tabus.size();
				if (tabusSize == newTabuSize) {
					continue;
				}
				//assign tasks
				assignTasks(projects, teams);
				//analizeResults
				int profit = resultAnalyser.calculateProfit(teams, projectsBackup);
				if (profit > maxProfit) {
					maxProfit = profit;
					bestResult = new ArrayList<>(projects);
				}
			} while (newTabuSize < tabuSize && isNotEndlessLoop(tabuSize, iterationCouter));
		}
		LOG.info("Assign tasks for teams");
		assignTasks(bestResult, teams);
		LOG.info("DONE");
	}

	private boolean isNotEndlessLoop(int tabuSize, int iterationCouter) {
		return iterationCouter < tabuSize*100;
	}

	private List<Project> createTabuNeighbourhood(ImmutableList<Project> projectsBackup, int i) {
		List<Project> projects = new ArrayList<>(projectsBackup.asList());
		Project first = projects.remove(i % projectsBackup.size());
		projects.add(0, first);
		return projects;
	}

	private List<Project> permuteProjects(List<Project> projects, Set<ArrayList<String>> tabus) {
		int projectToSwap = selectRandomProjectWithoutRoot(projects);
		int project2ToSwap = selectRandomProjectWithoutRoot(projects);
		while (projectToSwap == project2ToSwap) {
			project2ToSwap = selectRandomProjectWithoutRoot(projects);
		}
		Collections.swap(projects, projectToSwap, project2ToSwap);
		ArrayList<String> tabu = new ArrayList<>();
		for (Project project : projects) {
			tabu.add(project.getName());
		}
		tabus.add(tabu);
		return projects;
	}

	private int selectRandomProjectWithoutRoot(List<Project> projects) {
		return (int) (Math.random() * (projects.size() - 1) + 1);
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
			throw new CannotCalculateException("Not possible to calculate. No required teams.");
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
	}
}
