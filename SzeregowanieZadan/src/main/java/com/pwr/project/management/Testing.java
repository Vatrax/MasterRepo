package com.pwr.project.management;

import com.pwr.project.management.algorythm.analyser.AlgorithmAnalyser;
import com.pwr.project.management.algorythm.project.Algorithm;
import com.pwr.project.management.algorythm.project.AntColonyAlgorithm;
import com.pwr.project.management.algorythm.project.BasicFlowAlgorithm;
import com.pwr.project.management.algorythm.project.TabuSearchAlgorithm;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Testing {

    public static final int TEST = 150;
    public static final int[] NUMBERS_OF_PROJECTS = new int[]{25, 50, 100, 250, 500,1000};

    public static void main(String[] args) {
        List<Team> teams = createTeams();
        for (int numbersOfProject : NUMBERS_OF_PROJECTS) {
            System.out.println("\ntest for:" + numbersOfProject);
            executeTesting(teams, numbersOfProject);
        }
//        executeTesting(teams, TEST);
    }


    private static void executeTesting(List<Team> teams, int numbersOfProject) {
        List<Project> projects = createProjects(numbersOfProject);
        printResults(projects, teams, new BasicFlowAlgorithm());
//        printResults(projects, teams, new TabuSearchAlgorithm(0.25, 0.75));
//        printResults(projects, teams, new TabuSearchAlgorithm(0.25, 0.25));
//        printResults(projects, teams, new TabuSearchAlgorithm(0.75, 0.25));
//        printResults(projects, teams, new TabuSearchAlgorithm(0.75, 0.75));
        printResults(projects, teams, new AntColonyAlgorithm());
        printResults(projects, teams, new TabuSearchAlgorithm());
    }

    private static void printResults(List<Project> projects, List<Team> teams, Algorithm algorithm) {
        AlgorithmAnalyser algorithmAnalyser = new AlgorithmAnalyser();
        algorithmAnalyser.runWithAnalyse(algorithm, projects, teams);
        System.out.println(algorithm.getClass().getSimpleName());
//        System.out.println(algorithmAnalyser.getTime());
//        System.out.println(algorithmAnalyser.getEndDate().getTime());
        System.out.println(algorithmAnalyser.getProfit());
    }

    private static List<Team> createTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("MainBrickLayer", Type.BRICKLAYER, 2));
        teams.add(new Team("MainElectrician", Type.ELECTRICIAN, 3));
        teams.add(new Team("MainPlumber", Type.PLUMBER, 4));
        teams.add(new Team("MainRenovator", Type.RENOVATOR, 3));
        teams.add(new Team("SecondaryRenovator", Type.RENOVATOR, 3));
        return teams;
    }

    private static List<Project> createProjects(int numberOfProjects) {
        List<Project> projects = new ArrayList<>();
        for (int j = 0; j < numberOfProjects; j++) {
            projects.add(createProject(j + 1, numberOfProjects));
        }
        Collections.shuffle(projects, new Random(System.nanoTime()));
        return projects;
    }

    private static Project createProject(int number, int numberOfProjects) {
        Instant expectedEnd = Instant.now().plus(15 * numberOfProjects / 3 + new Random().nextInt(7) - 3, ChronoUnit.DAYS);
        return new Project("Test" + number, createTasks(), new Random().nextInt(51) + 25, Date.from(expectedEnd), new Random().nextInt(11), -1 * new Random().nextInt(6));
    }

    private static List<Task> createTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(8, Type.BRICKLAYER));
        tasks.add(new Task(4, Type.ELECTRICIAN));
        tasks.add(new Task(6, Type.PLUMBER));
        tasks.add(new Task(12, Type.RENOVATOR));
        return tasks;
    }
}
