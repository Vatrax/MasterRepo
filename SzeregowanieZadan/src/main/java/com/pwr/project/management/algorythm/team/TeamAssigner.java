package com.pwr.project.management.algorythm.team;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;

import java.util.List;

/**
 * Created by krzaczek on 6/7/15.
 */
public interface TeamAssigner {

	void assignTasks(List<Project> projects, List<Team> teams);

}