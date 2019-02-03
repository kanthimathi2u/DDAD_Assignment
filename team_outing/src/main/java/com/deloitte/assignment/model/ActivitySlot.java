package com.deloitte.assignment.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deloitte.assignment.vo.Task;

/*
 * Class for a group of tasks with a delimited size
 *
 */

public class ActivitySlot {

	private static final Logger logger = LoggerFactory.getLogger(ActivitySlot.class);
	
	private List<Task> tasks;

	private Integer size;

	public ActivitySlot(Integer size) {
		this.tasks = new ArrayList<>();
		this.size = size;
	}

	/*
	 * Method to add a task if there is enough free size
	 * 
	 * @param task the task to add
	 * 
	 * @return
	 * 
	 */
	
	public String addTask(Task task) {
		
		if ((this.getAvailableSize() < 15) && (this.getAvailableSize() >= 0)) {
			//System.out.println("Lunch Break to be added");			
			return "break";
		} else if (task != null && task.getDuration() <= this.getAvailableSize()) {
			logger.info(this.getAvailableSize() + " is available for task ::" + task.getName() + "::" );
			this.tasks.add(task);
			return "true";
		}
		return "false";
	}
	
	/*
	 * Method to add a task if there is enough free size
	 * 
	 * @param task the task to add
	 * 
	 * @return
	 * 
	 */
	
	public String addBreakTask(Task task) {
		
		if (task != null) {
			this.tasks.add(task);
			return "true";
		} 
		
		return "false";
	}
		
	/*
	 * Method to retrieve the available size
	 * 
	 * @return available size
	 * 
	 */
	
	public Integer getAvailableSize() {
		return this.size - tasks.stream().mapToInt(task -> task.getDuration()).sum();
	}

	/*
	 * Method to retrieve the used size
	 * 
	 * @return used size
	 * 
	 */
	
	public Integer getUsedSize() {
		return tasks.stream().mapToInt(task -> task.getDuration()).sum();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		tasks.forEach(task -> buffer.append(task).append(System.getProperty("line.separator")));
		return buffer.toString();
	}

}
