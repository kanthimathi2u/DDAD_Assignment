package com.deloitte.assignment.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.deloitte.assignment.vo.Task;

public class ActivitySlotTest {

	@Test
	public void addTask_ok() {
		// Given
		ActivitySlot block = new ActivitySlot(60);
		Task task = new Task("test", 60);
		// when
		String result = block.addTask(task);
		// Then
		assertTrue("Result must be true", (result=="true"?true:false));
		
	}
	
	@Test
	public void addTask_overSize() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		Task task = new Task("test", 60);
		// when
		String result = block.addTask(task);
		// Then
		assertFalse("Result must be false", (result=="true"?true:false));
	}
	
	@Test
	public void addTask_nullTask() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		// when
		String result = block.addTask(null);
		// Then
		assertFalse("Result must be false", (result=="true"?true:false));
	}
	
	@Test
	public void getAvailableSize_empy() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
	@Test
	public void getAvailableSize_partial() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		block.addTask(new Task("test", 20));
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 10", result == 10);
	}
	
	@Test
	public void getAvailableSize_full() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		block.addTask(new Task("test", 30));
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}
	
	
	@Test
	public void getUsedSize_empy() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}
	
	@Test
	public void getUsedSize_partial() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		block.addTask(new Task("test", 20));
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 20", result == 20);
	}
	
	@Test
	public void getUsedSize_full() {
		// Given
		ActivitySlot block = new ActivitySlot(30);
		block.addTask(new Task("test", 30));
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
}
