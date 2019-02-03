package com.deloitte.assignment.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deloitte.assignment.exception.TeamOutException;
import com.deloitte.assignment.file.FileUtils;
import com.deloitte.assignment.vo.Task;


/*
 * Class to manage an team outing schedule
 *
 */

public class TeamOutSchedule {
	private static final Logger logger = LoggerFactory.getLogger(TeamOutSchedule.class);
	String fileProp = "assignment.properties";
	
	FileUtils fileUtils = new FileUtils();
	Properties prop = fileUtils.readProperties(fileProp);

	List<DayProgram> programs = new ArrayList<>();

	public TeamOutSchedule(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart, LocalTime eveningEnd,
			Integer eveningExtraTime, Integer numberOfPrograms) throws TeamOutException {

		for (int i = 0; i < numberOfPrograms; i++) {
			programs.add(new DayProgram(morningStart, morningEnd, eveningStart, eveningEnd, eveningExtraTime));
		}
	}

	/*
	 * Method to add a list of tasks into the team outing schedule
	 * 
	 * @param tasks The list of tasks to be added
	 * 
	 * @throws TeamOutException
	 *  
	 */
	
	public void addTasks(List<Task> tasks) throws TeamOutException {

		this.checkTasks(tasks);

		List<Task> orderedTasks = tasks.stream().sorted((t1, t2) -> t2.getDuration() - t1.getDuration()).collect(Collectors.toList());;
		
		for (Task task : orderedTasks) {
			boolean result = this.addSingleTask(task);
			if (!result) {
				logger.debug("TeamOutSchedule.addTasks: There was a problem inserting task (" + task.getName() + ")");
				throw new TeamOutException("TeamOutSchedule.addTasks: There was a problem inserting task (" + task.getName() + ")");
			}
		}		
	}

	/*
	 * Method to check tasks before insert
	 * 
	 * @param tasks The tasks to be checked
	 * 
	 * @throws TeamOutException
	 *  
	 */
	
	private void checkTasks(List<Task> tasks) throws TeamOutException {

		Integer tasksTime = tasks.stream().mapToInt(task -> task.getDuration()).sum();
		Integer minTime = programs.stream().mapToInt(program -> program.getMinDuration()).sum();
		Integer maxTime = programs.stream().mapToInt(program -> program.getMaxDuration()).sum();
		if (tasksTime < minTime || tasksTime > maxTime) {
			logger.debug("TeamOutSchedule.checkTasks: The list of tasks has not the proper duration");
			throw new TeamOutException("TeamOutSchedule.checkTasks: The list of tasks has not the proper duration");
		}
	}

	/*
	 * Method to add a task to one of the programs
	 * 
	 * @param task Task to add
	 * 
	 * @return
	 * 
	 */
	
	private boolean addSingleTask(Task task) {
		String strResult = "false";
		Iterator<DayProgram> iterator = programs.iterator();
		while (strResult != "true" && iterator.hasNext()) {
			DayProgram curMornPgm = (DayProgram) iterator.next();
			if (strResult == "false") {
				strResult = curMornPgm.insertMorningTask(task);
				if (strResult == "break") {
					//Task breakTask = new Task("Lunch Break (minimum)", 60);
					Task breakTask = new Task(prop.getProperty("brkTask"), Integer.parseInt(prop.getProperty("brkDuration")));
					strResult = curMornPgm.insertBreakTask(breakTask);
					strResult = curMornPgm.insertMorningTask(task);
				}
			}
		}
		iterator = programs.iterator();
		while (strResult != "true" && iterator.hasNext()) {
			strResult = ((DayProgram) iterator.next()).insertEveningTask(task);
		}
		return valResult(strResult);
	}
	
	/*
	 * Method to add the SMP task to the end of the schedule
	 * 
	 * @return
	 * 
	 */
	
	public boolean addSMPTask() {
		String strResult = "false";
		Iterator<DayProgram> iterator = programs.iterator();
		while (iterator.hasNext()) {
			DayProgram curEvnPgm = (DayProgram) iterator.next();
				
			//Task smpTask = new Task("Staff Motivation Presentation", 60);
			Task smpTask = new Task(prop.getProperty("smpTask"), Integer.parseInt(prop.getProperty("smpDuration")));
			strResult = curEvnPgm.insertSMPTask(smpTask);
		}
							
		return valResult(strResult);
	}
	
	/*
	 * Method to convert String to Boolean
	 *
	 * @param String value 
	 * 
	 * @return
	 * 
	 */
	
	private boolean valResult(String strResult) {
		boolean result = false;
		
		if (strResult == "true") {
			result = true;
		} 
					
		return result;
	}
	
	public List<DayProgram> getPrograms() {
		return programs;
	}

	public void setPrograms(List<DayProgram> programs) {
		this.programs = programs;
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer("");
		Integer count = 1;
		for (DayProgram dayProgram : programs) {
			buffer
			.append("Team " + count + ":")
			.append(System.getProperty("line.separator"))
			.append(dayProgram)
			.append(System.getProperty("line.separator"));
			count ++;
		}
		return buffer.append(System.getProperty("line.separator")).toString();
	}

}
