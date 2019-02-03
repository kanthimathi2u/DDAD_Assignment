package com.deloitte.assignment.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import com.deloitte.assignment.exception.TeamOutException;
import com.deloitte.assignment.vo.Task;

public class DayProgramTest {
	
	@Test
	public void insertMorningTask_ok() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		Task task = new Task("test", 60);
		// when
		String result = dayProgram.insertMorningTask(task);
		// Then
		assertTrue("Result must be true", (result=="true"?true:false));
	}
	
	@Test
	public void insertMorningTask_noSpace() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		Task task = new Task("test", 60);
		// when
		String result = dayProgram.insertMorningTask(task);
		// Then
		assertFalse("Result must be false", (result=="true"?true:false));
	}
	
	@Test
	public void insertMorningTask_nullTask() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		// when
		String result = dayProgram.insertMorningTask(null);
		// Then
		assertFalse("Result must be false", (result=="true"?true:false));
	}
	
	@Test(expected = TeamOutException.class)
	public void insertMorningTask_constructorFail() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(8, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		// when
		dayProgram.insertMorningTask(null);
		// Then -> exception
	}	
	
	@Test
	public void insertEveningTask_ok() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(12, 00),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		Task task = new Task("test", 60);
		// when
		String result = dayProgram.insertEveningTask(task);
		// Then
		assertTrue("Result must be true", (result=="true"?true:false));
	}
	
	@Test
	public void insertEveningTask_noSpace() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		Task task = new Task("test", 60);
		// when
		String result = dayProgram.insertEveningTask(task);
		// Then
		assertFalse("Result must be false", (result=="true"?true:false));
	}
	
	@Test
	public void insertEveningTask_nullTask() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(16, 00),
				0);
		// when
		String result = dayProgram.insertEveningTask(null);
		// Then
		assertFalse("Result must be false", (result=="true"?true:false));
	}
	
	@Test(expected = TeamOutException.class)
	public void insertEveningTask_constructorFail() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(12, 00),
				0);
		// when
		dayProgram.insertEveningTask(null);
		// Then -> exception
	}
	
	@Test
	public void getMinDuration_ok() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}
	
	@Test
	public void getMinDuration_ok_extra() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				10);
		// when
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}
	
	@Test
	public void getMinDuration_oneTask() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		dayProgram.insertMorningTask(new Task("test", 30));
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
	@Test
	public void getMaxDuration_ok() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		Integer result = dayProgram.getMaxDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}
	
	@Test
	public void getMaxDuration_ok_extra() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				10);
		// when
		Integer result = dayProgram.getMaxDuration();
		// Then
		assertTrue("Result must be 70", result == 70);
	}
	
	@Test
	public void getMaxDuration_oneTask() throws TeamOutException {
		// Given
		DayProgram dayProgram = new DayProgram(
				LocalTime.of(9, 00),
				LocalTime.of(9, 30),
				LocalTime.of(13, 00),
				LocalTime.of(13, 30),
				0);
		// when
		dayProgram.insertMorningTask(new Task("test", 30));
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 30", result == 30);
	}

}
