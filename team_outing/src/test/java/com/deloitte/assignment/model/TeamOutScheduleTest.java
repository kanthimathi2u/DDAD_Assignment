package com.deloitte.assignment.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.deloitte.assignment.exception.TeamOutException;
import com.deloitte.assignment.vo.Task;

public class TeamOutScheduleTest {
	
	@Test
	public void addTasks_ok() throws TeamOutException {
		// Given
		TeamOutSchedule teamOutSchedule = new TeamOutSchedule(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0,2);
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task("testActivity1", 60));
		tasks.add(new Task("testActivity2", 15));
		tasks.add(new Task("testActivity3", 60));
		tasks.add(new Task("testActivity4", 30));
		tasks.add(new Task("testActivity5", 60));
		tasks.add(new Task("testActivity6", 60));
		tasks.add(new Task("testActivity7", 30));
		tasks.add(new Task("testActivity8", 60));
		tasks.add(new Task("testActivity9", 60));
		tasks.add(new Task("testActivity10", 15));
		tasks.add(new Task("testActivity11", 60));
		tasks.add(new Task("testActivity12", 60));
		tasks.add(new Task("testActivity13", 60));
		tasks.add(new Task("testActivity14", 60));
		tasks.add(new Task("testActivity15", 30));
		// when
		teamOutSchedule.addTasks(tasks);
		// Then -> no exception
	}
	
	@Test(expected = TeamOutException.class )
	public void addTasks_fewTasks() throws TeamOutException {
		// Given
		TeamOutSchedule teamOutSchedule = new TeamOutSchedule(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0,2);
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task("testActivity1", 60));
		tasks.add(new Task("testActivity2", 15));
		tasks.add(new Task("testActivity3", 60));
		tasks.add(new Task("testActivity4", 30));
		tasks.add(new Task("testActivity5", 60));
		tasks.add(new Task("testActivity6", 60));
		tasks.add(new Task("testActivity7", 30));
		tasks.add(new Task("testActivity8", 60));
		tasks.add(new Task("testActivity9", 60));
		tasks.add(new Task("testActivity10", 15));
		tasks.add(new Task("testActivity11", 60));
		tasks.add(new Task("testActivity12", 60));
		tasks.add(new Task("testActivity13", 60));
		tasks.add(new Task("testActivity14", 60));
		tasks.add(new Task("testActivity15", 15));
		// when
		teamOutSchedule.addTasks(tasks);
		// Then -> exception
	}
	
	@Test(expected = TeamOutException.class )
	public void addTasks_manyTasks() throws TeamOutException {
		// Given
		TeamOutSchedule teamOutSchedule = new TeamOutSchedule(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0,2);
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task("testActivity1", 60));
		tasks.add(new Task("testActivity2", 15));
		tasks.add(new Task("testActivity3", 60));
		tasks.add(new Task("testActivity4", 30));
		tasks.add(new Task("testActivity5", 60));
		tasks.add(new Task("testActivity6", 60));
		tasks.add(new Task("testActivity7", 30));
		tasks.add(new Task("testActivity8", 60));
		tasks.add(new Task("testActivity9", 60));
		tasks.add(new Task("testActivity10", 15));
		tasks.add(new Task("testActivity11", 60));
		tasks.add(new Task("testActivity12", 60));
		tasks.add(new Task("testActivity13", 60));
		tasks.add(new Task("testActivity14", 60));
		tasks.add(new Task("testActivity15", 60));
		// when
		teamOutSchedule.addTasks(tasks);
		// Then -> exception
	}

}
