package todo;

	
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Scanner;

	// this class contains the method which run todolist
	public class TodoList {

		//declaring Scanner
	    Scanner scan = new Scanner(System.in);
		
	    //variable finished for first loop which navigate welcome menu
		private boolean finished = false;
		
		//ready variable for second loop which navigate second menu for changing tasks
		private boolean ready = false;
		
		// ArrayList which contains tasks 
		private ArrayList<Task> tasks;
		
		//this variable storing tasks which are marked as done
		//int howManyDone = 0;
		
		//default constructor which initialize an ArrayList of tasks
		public TodoList() {
			this.tasks = new ArrayList<>();
		}
		
		/*
		 * this method read user input but only for String 
		 */
		public String inputString() {
			String text;
			text = scan.nextLine();
			if(text.isEmpty()) {
				text = scan.nextLine();
			}
			return text;
		}
		
		/*
		 * this method read user input for Integers
		 */
		public Integer inputInt() {
			int integer = 0;
			   try
			    {
			    	integer = Integer.parseInt(scan.next());
			    }
			  
			   catch( java.lang.NumberFormatException ex) {
				   System.out.println("Integers only, please.");
			   }
			return integer;
		}
		
		/**
	     * Add the task saved in the given filename to the current list.
	     * @param Method add task to ArrayList from the file
	     */
	    public void fileToArrayList(String filename, ArrayList<Task> tasks)
	    {
	    	//Creating object of TaskReader which contains method which read from file to ArrayList
	    	TaskReader reader = new TaskReader();
	    	//here tasks from ArrayList are add to ArrayList of tasks in this class
	        tasks.addAll(reader.getArrayFromFile(filename));
	    }
	    
	    
	    /*
	     * this method using FileOutPutStream to write tasks to a file 
	     */
	    public void writeTofile(String text) {
	    	try {	
	    	FileOutputStream fop = new FileOutputStream(new File("todolist.txt"));
			// get the content in bytes
			byte[] contentInBytes = text.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} 	
	    }
	 
	    /*
	     * this method is looking for a task by its ID number
	     * and returning this tasks if it exists 
	     * otherwise it return null
	     */
	    public Task findTask(String ID) {
			for (Task t : this.tasks) 
				if (t.getNumber().equals(ID)) 
					return t;
			return null;
			}
	    
	    /*
	     * this method removing task by given ID number
	     */
	    public void removeTask(String ID) {
			Task t = this.findTask(ID);
			if (t != null) {
			tasks.remove(t);
			}
			}
		
	    /**
	     * Print details of all the tasks.
	     */
	    public void printList()
	    {
	        tasks.forEach(task -> System.out.println(task.getDetails()));
	    }
	    
	   
	    /**
	     * Print all the tasks by the given date.
	     * @param String date.
	     */
	    public void printTaskByDate(String date)
	 {
	        tasks.stream()
	                 .filter(task -> task.getDate().equals(date))
	                 .map(task -> task.getDetails())
	                 .forEach(details -> System.out.println(details));        
	    }
	    
	    /*
	     * 
	     * Print all tasks by the given project ID
	     * @param String projectID
	     */
	    public void printTaskByProject(String projectID)
	    {
	           tasks.stream()
	                    .filter(task -> task.getProjectID().equals(projectID))
	                    .map(task -> task.getDetails())
	                    .forEach(details -> System.out.println(details));        
	       }
	   
	    /*
	     * this method is returning integer with number of tasks which are marked as Undone
	     */
	    public int howManyUndone() {
	    	int size = 0;
	    	for (Task t : this.tasks) 
				if (t.getDone().equals("Undone")) 
					size ++;
	    	return size;
	    }
	    
	    /*
	     * this method is counting tasks which are marked as done
	     * number of tasks are saved in howManyDone variable
	     * this method is used in play method
	     */
	    public int howManyDoneMethod() {
	    	int howManyDone = 0;
			for(int i = 0; i < tasks.size(); i++) {
				if(tasks.get(i).getDone().equals("Done")) {
					howManyDone ++;
				}
			}
			return howManyDone;
		}
	    
	   
	    /*
	     * this method is printing Welcome Menu 
	     * and also showing number of done and undone tasks
	     */
	public void printWelcome() {
			
			String line = ">> You have " + howManyUndone() + " tasks todo and " + howManyDoneMethod() + " tasks are done!";
			System.out.println(">> Welcome to ToDoList");
		    System.out.println(line);
		    System.out.println(">> Pick an option:");
		    System.out.println(">> (1) Show Task List (by date or project)");
		    System.out.println(">> (2) Add New Task");
		    System.out.println(">> (3) Edit Task (update, mark as done, remove)");
		    System.out.println(">> (4) Save and Quit");
		    System.out.println(">> ");
		}

	/*
	 * this method is printing another menu for editing a task 
	 * it asks what we want to change in a given task 
	 */
	public void printChangeMenu() {
		System.out.println("What do you want to do?\n1. Change the title\n2. Mark as done\n3. Change date\n4. Remove\n5. Change "
				+ "task ID\n6. Go to main menu\n" );
	}

	/*
	 * this method is printing if we want to continue or not 
	 * if yes so it show us Welcome Menu again
	 * if not it quitting program
	 */
		public void printQuitOrNo() {
			System.out.println("Do you want to continue? Y/N:");
			String YesOrNo = inputString();
			if(YesOrNo.charAt(0) == 'Y' || YesOrNo.charAt(0) == 'y') {
				printWelcome();
			}
			else if(YesOrNo.charAt(0) == 'N' || YesOrNo.charAt(0) == 'n') {
				finished = true;
				System.out.println("Bye!");
			}
			else {
				System.out.println("Invalid input: You have to write Y or N!\n");
				
				printQuitOrNo();
			}

	}
		/*
		 * similar method which printing if we want to continue or not 
		 * it is made for the second menu when we can change tasks
		 * if yes we go back to menu for changing tasks 
		 * if not we go back to main menu 
		 * 
		 */
		public void printQuitOrNoForSecondLoop() {
			System.out.println("Do you want to change something more with this task? Y/N:");
			String YesOrNo = inputString();
			if(YesOrNo.charAt(0) == 'Y' || YesOrNo.charAt(0) == 'y') {
				printChangeMenu();
			}
			else if(YesOrNo.charAt(0) == 'N' || YesOrNo.charAt(0) == 'n') {
				ready = true;
				printWelcome();
			}
			else {
				System.out.println("Invalid input: You have to write Y or N!\n");
				
				printQuitOrNoForSecondLoop();
			}

	}
		
		/*
		 * this method is converting tasks in ArrayList to a String 
		 * this String is later write to a file
		 */
		public String arrayToString(ArrayList <Task> tasks) {
			Task task;
			String taskDetails = "";
			for(int i = 0; i < tasks.size(); i++) {
				task = tasks.get(i);
				taskDetails += task.getNumber() + "," + task.getTitle() + "," + task.getDate() + "," + task.getDone() + "," + task.getProjectID() + "\n";
				
			}
			return taskDetails;
		}
		
		/*
		 * this method is checking if user input is empty
		 */
		public String checkIfEmpty(String text) {
			while(text.equals("")) {
				System.out.println("You have to write something!");
				text = inputString();
			}
			return text;
		}
		
		
		//method which initialize the to do list
	
		public void play() {
			
			//here we take all tasks from file to ArrayList tasks
			fileToArrayList("todolist.txt", tasks);
			// here we count how many tasks are done
			howManyDoneMethod();
			//printing main menu
			printWelcome();
			
			//while loop which provide navigation through main menu 
			while (finished == false) {
			
			int choose = inputInt();
		
			//switch choice for menu 
			switch(choose) {
			case 1:
				System.out.println("1.By date?\n2.By project ?");	
				
				//if by date so user enters 1
				//if by project user enters 2
				int choose2 = inputInt();
				
				if(choose2 == 1) {
					System.out.println("Write a date DDMMYY:");	
					String date = inputString();
					date = checkIfEmpty(date);
					//call to method which prints tasks by date 
					printTaskByDate(date);
						}
				
				else  if(choose2 == 2){
				System.out.println("Write project's ID number:");
				String id = inputString();
				id = checkIfEmpty(id);
				//call to method which prints tasks by project id
				printTaskByProject(id);
				}
				System.out.println();
				//call to method which prints if user want to continue or not 
				printQuitOrNo();
				
				break;
			case 2:
				
				System.out.println("Write task's ID number");
				//user must first write task id number to find the task
				String IDNumber = inputString();
				//checking if input is not empty
				IDNumber = checkIfEmpty(IDNumber);
				//checking if task ID already exists
				while(findTask(IDNumber) != null) {
					System.out.println("Task with ID number " + IDNumber + " already exists!");
					System.out.println("Write other number: ");
					IDNumber = inputString();
				}

				System.out.println("Write task's title:");
				String title = inputString();
				title = checkIfEmpty(title);
				System.out.println("Write task's date DDMMYY");
				String date2 = inputString();
				date2 = checkIfEmpty(date2);
				
				boolean invalid = false;
				//this is made to ask user if this project is new or not
				//if new so user must enter n or N 
				//if not new so must enter c or C for current
				while (invalid == false) {
					System.out.println("New project or current project? N/C");
					char NewOrCurrent = scan.nextLine().charAt(0);	
				if (NewOrCurrent == 'N' || NewOrCurrent == 'n') {
					invalid = true;
					System.out.println("Write project id: ");
					
					String ID = inputString();
					//task is marked as undone by default
					String status = "Undone";
					Task task = new Task(IDNumber, title, date2, status, ID );
					//adding task to ArrayList tasks
					tasks.add(task);
					//short info that task is added
					System.out.println("You have added task to a project: " + ID);
				}
				else if (NewOrCurrent == 'C' || NewOrCurrent == 'c') {
					invalid = true;
					System.out.println("Write project id: ");
					String ID = inputString();
					ID = checkIfEmpty(ID);
					//task is marked as undone by default
					String status = "Undone";
					Task task = new Task(IDNumber, title, date2, status, ID );
					tasks.add(task);
					
					System.out.println("You have added one task to a project: " + ID);
				}
				else {
					System.out.println("Invalid input. Try again!");
					
					
				}
				}
				
				System.out.println();
				printQuitOrNo();
				
				break;
				
			case 3:
				System.out.println("Write task's ID: ");
				//first enter task id to find task user want to change 
				String taskID = inputString();
				taskID = checkIfEmpty(taskID);
				Task task = findTask(taskID);
				//if task with such id doesn't exists so this message show up
				if (task == null) {
					System.out.println("Such task doesn't exists. Write again: ");
					taskID = inputString();
					taskID = checkIfEmpty(taskID);
					task = findTask(taskID);
				}
				//call to method which prints menu to show choices the user have  
				printChangeMenu();
				
				while (ready == false ) {
					int choice = inputInt();
					
					switch(choice) {
					case 1:
						System.out.println("Write new title: ");
						String newTitle = inputString();
						newTitle = checkIfEmpty(newTitle);
						//changing title of the task
						task.setTitle(newTitle);
						System.out.println("The title has changed!");
						
						System.out.println();
						//user want to continue? call to method 
						printQuitOrNoForSecondLoop();
						
						break;
					case 2:
						//here we can change status of the task from undone to done if it is not already changed
						if(task.getDone().equals("Done")) {
							System.out.println("The task " + task.getNumber() + "is already done.");
						}
						else {
							task.setDone("Done");
							System.out.println("The task " + task.getNumber() + " is marked as done!");
						}
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						break;
					case 3:
						//changes the date of the task
						System.out.println("Write new date DDMMYY: ");
						String newDate = inputString();
						newDate = checkIfEmpty(newDate);
						task.setDate(newDate);
						System.out.println("The date has changed!");
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						break;
					case 4:
						//remove task
						removeTask(taskID);
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						break;
					case 5:
						//changes task id 
						System.out.println("Write new task id: ");
						String newID = inputString();
						newID = checkIfEmpty(newID);
						task.setNumber(newID);
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						break;
					case 6:
						//goes to main menu 
						ready = true;
						printWelcome();
						break;
					}
			 }
				break;
				
				
			case 4:
				//here we write all tasks to file and print them and quitting programm
				String text = arrayToString(tasks); // tasks to String
				writeTofile(text); //write task to file
				printList(); // print content
				finished = true;
				System.out.println();
				System.out.println("Goodbye!");
				
				
		}

		
			}
		}
		


}
