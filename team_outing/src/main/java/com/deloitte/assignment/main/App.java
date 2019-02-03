package com.deloitte.assignment.main;

import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deloitte.assignment.exception.TeamOutException;
import com.deloitte.assignment.file.FileUtils;
import com.deloitte.assignment.model.TeamOutSchedule;
import com.deloitte.assignment.vo.Task;

/*
 * Main class. Reads a file and generates the Team Outing Schedule
 *
 */

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	static String fileInput = "activities.txt";
		
	public static void main(String[] args) {

		FileUtils fileUtils = new FileUtils();
		List<Task> tasks;
		try {
			logger.info("Inside the Main App of the Assignment");
		
			tasks = fileUtils.loadTasksFromResources(fileInput);
			TeamOutSchedule teamOutSchedule = new TeamOutSchedule(
					LocalTime.of(9, 00), 
					LocalTime.of(12, 00), 
					LocalTime.of(13, 00),
					LocalTime.of(16, 00),
					60, 2);

			teamOutSchedule.addTasks(tasks);
			teamOutSchedule.addSMPTask();
			System.out.println("\n");
			System.out.println("************************************\n");
			System.out.println("Deloitte Digital Away Day Schedule:\n");
			System.out.println("************************************\n");
			System.out.println(teamOutSchedule);
			System.out.println("\n");
			
		} catch (TeamOutException e) {
			System.out.println("There was an error executing Deloitte Digital Away Day schedule :" + e.getLocalizedMessage());
		}
	}
}
