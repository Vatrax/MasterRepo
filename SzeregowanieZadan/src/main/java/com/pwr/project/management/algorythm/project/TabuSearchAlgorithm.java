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
 * Created by krzaczek on 25.04.15.
 */
public class TabuSearchAlgorithm implements Algorithm {

    private static final Logger LOG = Logger.getLogger("TabuSearchAlgorithm");
    private final double percentTabuSize;
    private final double percentIterations;
    private TeamAssigner teamAssigner = new BasicTeamAssigner();
    private ResultAnalyser resultAnalyser = new ResultAnalyser();

    public TabuSearchAlgorithm() {
        this.percentTabuSize = 1.0;
        this.percentIterations = 0.1;
    }

    public TabuSearchAlgorithm(double percentTabuSize, double percentIterations) {
        this.percentTabuSize = percentTabuSize;
        this.percentIterations = percentIterations;
    }

    @Override
    public void serialize(List<Project> projects, List<Team> teams) {
//		LOG.info("Init algorithm");
        if (projects.size() < 3) {
            throw new TooLowNumberOfProjectsException("To low number of projects");
        }
        int numberOfIterations = (int) Math.ceil(projects.size() * percentIterations);
        int tabuSize = (int) Math.ceil(projects.size() * percentTabuSize);
        int maxProfit = Integer.MIN_VALUE;
        ImmutableList<Project> projectsBackup = ImmutableList.copyOf(projects);
        List<Project> bestResult = null;
//		LOG.info("Start algorithm");
        for (int i = 0; i < numberOfIterations; i++) {
            projects = createTabuNeighbourhood(projectsBackup, i);
            Set<ArrayList<String>> tabus = new HashSet<>();
            int newTabuSize;
//			LOG.info("Searching Neighbours");
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
                teamAssigner.assignTasks(projects, teams);
                //analizeResults
                int profit = resultAnalyser.calculateProfit(teams, projectsBackup);
                if (profit > maxProfit) {
                    maxProfit = profit;
                    bestResult = new ArrayList<>(projects);
                }
            } while (newTabuSize < tabuSize && isNotEndlessLoop(tabuSize, iterationCouter));
        }
//		LOG.info("Assign tasks for teams");
        teamAssigner.assignTasks(bestResult, teams);
//		LOG.info("DONE");
    }

    private boolean isNotEndlessLoop(int tabuSize, int iterationCouter) {
        return iterationCouter < tabuSize * 100;
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
}
