package com.deloitte.assignment.vo;

import java.time.LocalTime;

/*
 * Class for task information
 *
 */

public class Task {
	
	static String SPRINT = "sprint";

	private String name;

	private LocalTime startTime;

	private Integer duration;

	public Task(String name, Integer duration) {
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		buffer
		.append(this.startTime + " ")
		.append((this.getStartTime().getHour()<12?"am":"pm"))
		.append(" : " + this.getName() + " ")
		.append((this.duration == 15?SPRINT:this.duration+"min"));
		return buffer.toString();
		
	}

}
