package com.deloitte.assignment.file;

import org.junit.Test;

import com.deloitte.assignment.exception.TeamOutException;

public class FileUtilsTest {
	

	@Test
	public void loadTasks_ok() throws TeamOutException {
		// Given
		String fileName = "input_test.txt";
		FileUtils fileUtils = new FileUtils();
		// when
		fileUtils.loadTasksFromResources(fileName);
		// Then -> no exception
	}
	
	@Test(expected = TeamOutException.class)
	public void loadTasks_no_file() throws TeamOutException {
		// Given
		String fileName = "input_tes_no_file.txt";
		FileUtils fileUtils = new FileUtils();
		// when
		fileUtils.loadTasksFromResources(fileName);
		// Then -> exception
	}
	
	@Test(expected = TeamOutException.class)
	public void loadTasks_wrong_1() throws TeamOutException {
		// Given
		String fileName = "input_test_wrong_1.txt";
		FileUtils fileUtils = new FileUtils();
		// when
		fileUtils.loadTasksFromResources(fileName);
		// Then -> exception
	}
	
	@Test(expected = TeamOutException.class)
	public void loadTasks_wrong_2() throws TeamOutException {
		// Given
		String fileName = "input_test_wrong_2.txt";
		FileUtils fileUtils = new FileUtils();
		// when
		fileUtils.loadTasksFromResources(fileName);
		// Then -> exception
	}
	
	@Test(expected = TeamOutException.class)
	public void loadTasks_wrong_3() throws TeamOutException {
		// Given
		String fileName = "input_test_wrong_3.txt";
		FileUtils fileUtils = new FileUtils();
		// when
		fileUtils.loadTasksFromResources(fileName);
		// Then -> exception
	}
	
	@Test(expected = TeamOutException.class)
	public void loadTasks_wrong_4() throws TeamOutException {
		// Given
		String fileName = "input_test_wrong_4.txt";
		FileUtils fileUtils = new FileUtils();
		// when
		fileUtils.loadTasksFromResources(fileName);
		// Then -> exception
	}
	
	@Test(expected = TeamOutException.class)
	public void loadTasks_wrong_5() throws TeamOutException {
		// Given
		String fileName = "input_test_wrong_5.txt";
		FileUtils fileUtils = new FileUtils();
		// when
		fileUtils.loadTasksFromResources(fileName);
		// Then -> exception
	}

}
