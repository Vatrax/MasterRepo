package com.pwr.project.management.algorythm.project;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;

import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 07.03.15.
 */
public interface Algorithm {

	void serialize(List<Project> projects, List<Team> teams);

}
