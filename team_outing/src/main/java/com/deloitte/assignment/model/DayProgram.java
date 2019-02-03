package com.deloitte.assignment.model;

import java.time.Duration;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deloitte.assignment.exception.TeamOutException;
import com.deloitte.assignment.vo.AdditionalTimeSlot;
import com.deloitte.assignment.vo.Task;

/*
 * Class to manage a day program with two blocks (morning and evening)
 *
 */

public class DayProgram {
	
	private static final Logger logger = LoggerFactory.getLogger(DayProgram.class);

	private LocalTime morningStart;
	private ActivitySlot morning;

	private LocalTime eveningStart;
	private AdditionalTimeSlot evening;
	
	public DayProgram(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart, LocalTime eveningEnd,
			Integer eveningExtraTime) throws TeamOutException {

		this.checkDayProgram(morningStart, morningEnd, eveningStart, eveningEnd);

		this.morningStart = morningStart;
		this.morning = new ActivitySlot(getBlockSize(morningStart, morningEnd));
		this.eveningStart = eveningStart;
		this.evening = new AdditionalTimeSlot(getBlockSize(eveningStart, eveningEnd), eveningExtraTime);
	}

	/*
	 * Method to insert a task in morning block
	 * 
	 * @param task the task to insert
	 * 
	 * @return "true" if inserted
	 * 
	 */
	
	public String insertMorningTask(Task task) {
		
		if (task != null){
			task.setStartTime(this.morningStart.plusMinutes(morning.getUsedSize()));
			return this.morning.addTask(task);
		}
		return "false";
	}

	/*
	 * Method to insert a task in evening block
	 * 
	 * @param task the task to insert
	 * 
	 * @return "true" if inserted
	 * 
	 */
	
	public String insertEveningTask(Task task) {
		
		if (task != null){
			task.setStartTime(this.eveningStart.plusMinutes(evening.getUsedSize()));
			 return this.evening.addTask(task);
		}
		return "false";
	}

	/*
	 * Method to insert a break task in morning block
	 * 
	 * @param task the task to insert
	 * 
	 * @return "true" if inserted
	 * 
	 */
	
	public String insertBreakTask(Task task) {
		
		if (task != null){
			task.setStartTime(this.morningStart.plusMinutes(morning.getUsedSize()));
			return this.morning.addBreakTask(task);
		}
		return "false";
	}
	
	/*
	 * Method to insert SMP task in evening block
	 * 
	 * @param task the task to insert
	 * 
	 * @return "true" if inserted
	 * 
	 */
	
	public String insertSMPTask(Task task) {
		
		if (task != null){
			task.setStartTime(this.eveningStart.plusMinutes(evening.getUsedSize()));
			return this.evening.addBreakTask(task);
		}
		return "false";
	}

	/*
	 * Method to get block size based in start and end time
	 *  
	 * @param start the LocalTime when this block starts 
	 * @param end the LocalTime when this block ends
	 * 
	 * @return block size in minutes
	 * 
	 * @throws Exception
	 * 
	 */
	
	private Integer getBlockSize(LocalTime start, LocalTime end) throws TeamOutException {

		Duration duration = Duration.between(start, end);
		if (duration.getSeconds() > 0) {
			return (int) (duration.getSeconds() / 60);
		}
		throw new TeamOutException("DayProgram.getBlockSize: End time must be after start time");
	}

	/*
	 * Method to get the min day program duration in minutes
	 * 
	 * @return Day program min duration (minutes)
	 * 
	 */
	
	public Integer getMinDuration() {
		return this.morning.getAvailableSize() + this.evening.getAvailableSize() - this.evening.getExtraTime();
	}

	/*
	 * Method to get the max day program duration in minutes
	 * 
	 * @return Day program max duration (minutes)
	 * 
	 */
	
	public Integer getMaxDuration() {
		return this.morning.getAvailableSize() + this.evening.getAvailableSize();
	}

	private boolean checkDayProgram(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart,
			LocalTime eveningEnd) throws TeamOutException {

		if (morningStart != null && eveningStart != null && eveningStart != null && eveningEnd != null
				&& eveningEnd.isAfter(morningStart)) {
			return true;
		} else {
			logger.debug("DayProgram.checkDayProgram: There was a problem creating the program");
			throw new TeamOutException("DayProgram.checkDayProgram: There was a problem creating the program");
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		buffer.append(morning);
		buffer.append(evening);
		return buffer.toString();
	}

}
