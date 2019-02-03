package com.deloitte.assignment.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deloitte.assignment.exception.TeamOutException;
import com.deloitte.assignment.vo.Task;

/*
 * Class to manage file load and save
 * 
 */

public class FileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	static String REGEX = "^[a-zA-Z0-9][a-zA-Z0-9& -]* (([0-9]+min)|sprint)$";
	static String SPRINT = "sprint";
	
	public Properties readProperties(String conFile) throws TeamOutException {
		InputStream input = null;
		Properties prop = new Properties();
		try 
		{
			input = getClass().getClassLoader().getResourceAsStream(conFile);
			//input = new FileInputStream(conFile);
			//	load a properties file
			prop.load(input);
			// 	get the property value and print it out
			logger.info(prop.getProperty("brkTask"));
			logger.info(prop.getProperty("brkDuration"));
			logger.info(prop.getProperty("smpTask"));
			logger.info(prop.getProperty("smpDuration"));
		} catch (FileNotFoundException ex) {
			logger.debug("FileUtils:readProperties: FileNotFoundException" + ex.getMessage());
			ex.printStackTrace();
			throw new TeamOutException("FileUtils:readProperties: FileNotFoundException" + ex.getMessage());
		} catch (IOException ex) {
			logger.debug("FileUtils:readProperties: IOException" + ex.getMessage());
			ex.printStackTrace();
			throw new TeamOutException("FileUtils:readProperties: IOException" + ex.getMessage());
		} catch (Exception ex) {
			logger.debug("FileUtils:readProperties: Generic Exception" + ex.getMessage());
			ex.printStackTrace();
			throw new TeamOutException("FileUtils:readProperties: Generic Exception" + ex.getMessage());
		} finally {
			if (input != null) 
			{
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	/*
	 * Method to generate a list of tasks from a file stored in resources
	 * 
	 * @param fileName File name in resources
	 * 
	 * @return List of tasks
	 * 
	 * @throws TeamOutException
	 * 
	 */
	
	public List<Task> loadTasksFromResources(String fileName) throws TeamOutException {
		List<Task> tasks = new ArrayList<>();
		try {
			Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI());
			Stream<String> lines;
			lines = Files.lines(path);
			Iterator<String> iterator = lines.iterator();
			while (iterator.hasNext()) {
				String line = iterator.next();
				if (Pattern.matches(REGEX, line)) {
					Integer duration = this.getMinutes(line.substring(line.lastIndexOf(" ") + 1));
					tasks.add(new Task(line.substring(0, line.lastIndexOf(" ")), duration));
				}else{
					lines.close();
					logger.debug("FileUtils:loadTasksFromResources: File line " + line + " has not a valid format");
					throw new TeamOutException("FileUtils:loadTasksFromResources: File line " + line + " has not a valid format");
				}
			}
			lines.close();
		} catch (Exception ex) {
			logger.debug("FileUtils:loadTasksFromResources: there was a problem loading tasks from file:" + ex.getLocalizedMessage());
			throw new TeamOutException("FileUtils:loadTasksFromResources: there was a problem loading tasks from file:" + ex.getLocalizedMessage());
		}
		return tasks;
	}

	/*
	 * Method to get minutes value
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @throws TeamOutException
	 *  
	 */
	
	private Integer getMinutes(String value) throws TeamOutException {
		Integer result = 0;
		if (SPRINT.equals(value))
			result = 15;
		else
			result = Integer.valueOf(value.substring(0, value.length() - 3));
		
		if (result <=0 || result > 60){
			logger.debug("FileUtils:getMinutes: invalid task duration: " + result);
			throw new TeamOutException("FileUtils:getMinutes: invalid task duration: " + result);
		}
		return result;
	}

}
