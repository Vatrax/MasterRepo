package com.pwr.project.management.presenter;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.algorythm.BasicFlowAlgorithm;
import com.pwr.project.management.algorythm.analyser.AlgorithmAnalyser;
import com.pwr.project.management.listeners.AlgorithmChangeListener;
import com.pwr.project.management.ui.partial.AlgorithmManagementLayout;
import com.vaadin.ui.Layout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 21.03.15.
 */
public class AlgorythmManagementPresenter {
	private GanttPresenter ganttPresenter;
	private List<Algorithm> algorithms = new ArrayList<>();
	private AlgorithmManagementLayout layout;

	public AlgorythmManagementPresenter(GanttPresenter ganttPresenter) {
		this.ganttPresenter = ganttPresenter;
		populateAlgorithms();
	}

	private void populateAlgorithms() {
		algorithms.add(new BasicFlowAlgorithm());
	}

	public Layout createLayout() {
		layout = new AlgorithmManagementLayout(algorithms,
				new AlgorithmChangeListener(this, ganttPresenter, algorithms));
		return layout;
	}

	public void setTime(long time) {
		layout.setTime("Time: " + time + "ms");
	}

	public void setDate(Date cMax) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(cMax);
		String date = String
				.format("End date: %s.%s.%s", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.YEAR));
		layout.setDate(date);
	}

	public void setProfit(int profit) {
		layout.setProfit("Profit: " + profit + "$");
	}

	public void updateAlgorythmResults(AlgorithmAnalyser algorithmAnalyser) {
		setTime(algorithmAnalyser.getTime());
		setDate(algorithmAnalyser.getEndDate());
		setProfit(algorithmAnalyser.getProfit());
	}
}
