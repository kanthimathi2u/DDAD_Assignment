DDAD_Assignment

Deloitte Digital Away Day is a Java application to generate the teams' schedule for the given set of activities for the day.

Developed By
Kanthimathi Ramaswamy (kanthimathi2u@gmail.com)

Tech Stack
1.	Java 1.8
2.	Apache Maven 3.6.0
3.	Junit 4.12
4.	slf4j + log4j 1.7.25

Project Setup
DDAD Assignment generates the teams' schedule for the given set of activities based on the input file located at src\main\resources\activities.txt
        The activities in activities.txt are as per the given format:
        [activity_name] [time]min
        [activity_name] sprint
   Sprint is used for 15 minutes activities.
        
        Example activities list:
          Duck Herding 60min
          Archery 45min
          Learning Magic Tricks 40min
          Laser Clay Shooting 60min
          Human Table Football 30min
          Buggy Driving 30min
          Salsa & Pickles sprint
          2-wheeled Segways 45min
          Viking Axe Throwing 60min
          Giant Puzzle Dinosaurs 30min
          Giant Digital Graffiti 60min
          Cricket 2020 60min
          Wine Tasting sprint
          Arduino Bonanza 30min
          Digital Tresure Hunt 60min
          Enigma Challenge 45min
          Monti Carlo or Bust 60min
          New Zealand Haka 30min
          Time Tracker sprint
          Indiano Drizzle 45min
     
Log setup is based on the file located at src\main\resources\log4j.properties
  Example Log Setup:
          # Set root logger level to DEBUG and its only appender to A1.
          log4j.rootLogger=DEBUG, A1
          # A1 is set to be a ConsoleAppender.
          log4j.appender.A1=org.apache.log4j.ConsoleAppender
          # A1 uses PatternLayout.
          log4j.appender.A1.layout=org.apache.log4j.PatternLayout
          log4j.appender.A1.layout.ConversionPattern=%-4r [%t] %-5p %c %x - %m%n
    
Other Generic activities like 'lunch break' and 'Staff Motivation Presentation' activities along with their duration details are listed in src\main\resources\assignment.properrties
    Example properties content:
          #to add Generic Activities in addition to the input tasks
          brkTask=Lunch Break
          brkDuration=60
          smpTask=Staff Motivation Presentation
          smpDuration = 60

Design Details:

com.deloitte.assignment.main -	App.java	This is the main method to start the application.

com.deloitte.assignment.file -	FileUtils.java	This class has methods for reading the list of activities & reading the properties file contents.

com.deloitte.assignment.model -	ActivitySlot.java	This class has the methods to calculate the available and used time slots of the sessions. This also has the method to add activities into the sessions.

com.deloitte.assignment.model -	DayProgram.java	This class has the Morning and Evening Session Schedules. It has the methods to include the activities into the sessions.

com.deloitte.assignment.model -	TeamOutSchedule.java	This class has the list of Schedule generation code based on the start & end times of the activities. Start time is calculated using the LocalTime.

com.deloitte.assignment.vo -	AdditionalTimeSlot.java	This is extended from the ActivitySlot class. Available size method is overwritten to manage the extra time.

com.deloitte.assignment.vo -	Task.java	This is the class to store a task name and duration.

com.deloitte.assignment.exception -	TeamOutExeception.java	This exception is thrown when a functional or execution error occurs.  

Build and Deployment

    C:\team_outing>mvn clean package
    C:\team_outing>mvn exec:java


Output

************************************

Deloitte Digital Away Day Schedule:

************************************

Team 1:
09:00 am : Duck Herding 60min
10:00 am : Laser Clay Shooting 60min
11:00 am : Viking Axe Throwing 60min
12:00 pm : Lunch Break (minimum) 60min
13:00 pm : Monti Carlo or Bust 60min
14:00 pm : Archery 45min
14:45 pm : 2-wheeled Segways 45min
15:30 pm : Enigma Challenge 45min
16:15 pm : Indiano Drizzle 45min
17:00 pm : Staff Motivation Presentation 60min

Team 2:
09:00 am : Giant Digital Graffiti 60min
10:00 am : Cricket 2020 60min
11:00 am : Digital Tresure Hunt 60min
12:00 pm : Lunch Break (minimum) 60min
13:00 pm : Learning Magic Tricks 40min
13:40 pm : Human Table Football 30min
14:10 pm : Buggy Driving 30min
14:40 pm : Giant Puzzle Dinosaurs 30min
15:10 pm : Arduino Bonanza 30min
15:40 pm : New Zealand Haka 30min
16:10 pm : Salsa & Pickles sprint
16:25 pm : Wine Tasting sprint
16:40 pm : Time Tracker sprint
16:55 pm : Staff Motivation Presentation 60min
