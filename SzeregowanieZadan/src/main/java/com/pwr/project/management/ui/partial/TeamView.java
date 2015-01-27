package com.pwr.project.management.ui.partial;

import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Team;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.VerticalLayout;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by krzaczek on 22.01.15.
 */
public class TeamView extends VerticalLayout {

	private Gantt gantt;
	private BeanContainer<String, Team> teams;

	public TeamView(BeanContainer<String, Team> teams) {
		this.teams = teams;
		init();
	}

	public void init() {
		gantt = new Gantt();
		gantt.setWidth(100, Unit.PERCENTAGE);
		gantt.setHeight(500, Unit.PIXELS);
		gantt.setResizableSteps(false);
		gantt.setMovableSteps(false);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		gantt.setStartDate(cal.getTime());
		cal.add(Calendar.YEAR, 1);
		gantt.setEndDate(cal.getTime());

		cal.setTime(new Date());
		addComponent(gantt);
		drawSteps();
		//		Step step1 = new Step("First step");
		//		step1.setStartDate(cal.getTime().getTime());
		//		cal.add(Calendar.MONTH, 2);
		//		step1.setEndDate(cal.getTime().getTime());
		//
		//		Step step2 = new Step("Second step");
		//		cal.add(Calendar.DATE, 1);
		//		step2.setStartDate(cal.getTime().getTime());
		//		cal.add(Calendar.MONTH, 4);
		//		step2.setEndDate(cal.getTime().getTime());
		//
		//		Step step3 = new Step("Third step");
		//		cal.add(Calendar.DATE, 1);
		//		step3.setStartDate(cal.getTime().getTime());
		//		cal.add(Calendar.MONTH, 12);
		//		step3.setEndDate(cal.getTime().getTime());
		//
		//		gantt.addStep(step1);
		//		gantt.addStep(step2);
		//		gantt.addStep(step3);
	}

	private void drawSteps() {
		int index = 0;
		System.out.println("drawing step");
		for (Iterator i = teams.getItemIds().iterator(); i.hasNext(); ) {
			Team team = teams.getItem(i.next()).getBean();
			System.out.println(team.getName() + " " + team.getType());
			for (AssignedTask task : team.getTasks()) {
				gantt.addStep(createStep(task));
				System.out.println(task.getProjectName() + " " +task.getStart()+ " " + task.getEnd());
			}
			index++;
		}
	}

	private Step createStep(AssignedTask task) {
		Step step = new Step();
		step.setStartDate(task.getStart());
		step.setEndDate(task.getEnd());
		step.setCaption(task.getProjectName());
		return null;
	}

}