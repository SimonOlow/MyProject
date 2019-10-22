package todo;



	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.function.Function;
	import java.util.stream.Collectors;


	public class TaskReader {
		
		 // How many fields are expected.
	    private static final int NUMBER_OF_FIELDS = 5;
	    // Index values for the fields in each record.
	    private static final int NUMBER = 0,
	                             TITLE = 1,
	                             DATE = 2,
	                             STATUS = 3,
	                             PROJECTID = 4;
	    
	    /**
	     * Create a SightingReader.
	     */
	    public TaskReader()
	    {
	    	//Erikas commenting again
	    }
	    
	    /**
	     * Read sightings in CSV format from the given file.
	     * Return an ArrayList of Sighting objects created from
	     * the information in the file.
	     * 
	     * @param filename The file to be read - should be in CSV format.
	     * @return A list of Sightings.
	     */
	    public ArrayList<Task> getArrayFromFile(String filename) 
	    {
	        // Create a task from a CSV input line.
	        Function<String, Task> createTaskList = 
	            record -> {
	                           String[] parts = record.split(",");
	                           if(parts.length == NUMBER_OF_FIELDS) {
	                               try {
	                                   String number = parts[NUMBER].trim();
	                                   String title = parts[TITLE].trim();
	                                   String date = parts[DATE].trim();
	                                   String status = parts[STATUS].trim();
	                                   String projectID = parts[PROJECTID].trim();
	                                   return new Task(number, title, date, status, projectID);
	                               }
	                               catch(NumberFormatException e) {
	                                   System.out.println("Error: " + record);
	                                   return null;
	                               }
	                           }
	                           else {
	                               System.out.println("Task record has the wrong number of fields: " + record);
	                               return null;
	                           }
	                       };
	        ArrayList<Task> task;
	        try {
	            task = Files.lines(Paths.get(filename))
	                             .filter(record -> record.length() > 0 && record.charAt(0) != '#')
	                             .map(createTaskList)
	                             .filter(sighting -> sighting != null)
	                             .collect(Collectors.toCollection(ArrayList::new));
	        }
	        catch(IOException e) {
	            System.out.println("Unable to open " + filename);
	            task = new ArrayList<>();
	        }
	        return task;
	    }

	}


