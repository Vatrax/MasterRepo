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

/**
 * Created by krzaczek on 6/7/15.
 */
public class AntColonyAlgorithm implements Algorithm {

    private final double percentOfAnts;
    private final double percentOfIterations;

//	private static final Logger LOG = Logger.getLogger("AntColonyAlgorithm");

    private TeamAssigner teamAssigner = new BasicTeamAssigner();
    private ResultAnalyser resultAnalyser = new ResultAnalyser();

    public AntColonyAlgorithm() {
        this.percentOfAnts = 1.0;
        this.percentOfIterations = 0.1;
    }

    public AntColonyAlgorithm(double percentOfAnts, double percentOfIterations) {
        this.percentOfAnts = percentOfAnts;
        this.percentOfIterations = percentOfIterations;
    }


    @Override
    public void serialize(List<Project> projects, List<Team> teams) {
        int numberOfAnts = (int) Math.ceil(projects.size() * percentOfAnts);
        int numberOfIterations = (int) Math.ceil(projects.size() * percentOfIterations);
//		LOG.info("Init algorithm");
        if (projects.size() < 3) {
            throw new TooLowNumberOfProjectsException("To low number of projects");
        }
        int maxProfit = Integer.MIN_VALUE;
        ImmutableList<Project> projectsBackup = ImmutableList.copyOf(projects);
        List<Project> bestResult = projects;
//		LOG.info("Start algorithm");
        for (int i = 0; i < numberOfIterations; i++) {
//			LOG.info("Looking for phereomone");
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
//		LOG.info("Assign tasks for teams");
        teamAssigner.assignTasks(bestResult, teams);
//		LOG.info("DONE");
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
}
