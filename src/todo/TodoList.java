package todo;

	
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Scanner;


	public class TodoList {

		
	Scanner scan = new Scanner(System.in);
		
		private boolean finished = false;
		private boolean ready = false;
		private ArrayList<Task> tasks;
		

		int howManyDone = 0;
		
		
		public TodoList() {
			
			this.tasks = new ArrayList<>();
		}
		
		public String inputString() {
			String text;
			text = scan.nextLine();
			if(text.isEmpty()) {
				text = scan.nextLine();
			}
			return text;
		}
		
		public Integer inputInt() {
			
			int integer = scan.nextInt();
			return integer;
		}
		
		/**
	     * Add the task saved in the given filename to the current list.
	     * @param Method add task to arraylist from the file
	     */
	    public void fileToArrayList(String filename, ArrayList<Task> tasks)
	    {
	    	TaskReader reader = new TaskReader();
	        tasks.addAll(reader.getArrayFromFile(filename));
	    }
	    
	    
	    
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
	 
	    
	    public Task findTask(String ID) {
			for (Task t : this.tasks) 
				if (t.getNumber().equals(ID)) 
					return t;
			return null;
			}
	    
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
	    
	    public void printTaskByProject(String projectID)
	    {
	           tasks.stream()
	                    .filter(task -> task.getProjectID().equals(projectID))
	                    .map(task -> task.getDetails())
	                    .forEach(details -> System.out.println(details));        
	       }
	   
	    public int howManyUndone() {
	    	int size = 0;
	    	for (Task t : this.tasks) 
				if (t.getDone().equals("Undone")) 
					size ++;
	    	return size;
	    }
	    
	   
	public void printWelcome() {
			
			String line = ">> You have " + howManyUndone() + " tasks todo and " + howManyDone + " tasks are done!";
			System.out.println(">> Welcome to ToDoLy");
		    System.out.println(line);
		    System.out.println(">> Pick an option:");
		    System.out.println(">> (1) Show Task List (by date or project)");
		    System.out.println(">> (2) Add New Task");
		    System.out.println(">> (3) Edit Task (update, mark as done, remove)");
		    System.out.println(">> (4) Save and Quit");
		    System.out.println(">> ");
		}

	public void printChangeMenu() {
		System.out.println("What do you want to do?\n1. Change the title\n2. Mark as done\n3. Change date\n4. Remove\n5. Change "
				+ "task ID\n6. Go to main menu\n" );
	}

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
		
		public void printQuitOrNoForSecondLoop() {
			System.out.println("Do you want to continue? Y/N:");
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
		
		public void howManyDoneMethod() {
			for(int i = 0; i < tasks.size(); i++) {
				if(tasks.get(i).getDone().equals("Done")) {
					howManyDone ++;
				}
			}
		}
	
		
		public String arrayToString(ArrayList <Task> tasks) {
			Task task;
			String taskDetails = "";
			for(int i = 0; i < tasks.size(); i++) {
				task = tasks.get(i);
				taskDetails += task.getNumber() + "," + task.getTitle() + "," + task.getDate() + "," + task.getDone() + "," + task.getProjectID() + "\n";
				
			}
			return taskDetails;
		}
		
		
		//method which initialize todolist
		public void play() {
			
			
			fileToArrayList("todolist.txt", tasks);
			howManyDoneMethod();
			
			printWelcome();
			
			
			
			while (finished == false) {
			int choose = inputInt();
			
			switch(choose) {
			case 1:
				System.out.println("1.By date?\n2.By project ?");	
				int choose2 = inputInt();
				
				if(choose2 == 1) {
					System.out.println("Write a date DDMMYY:");	
					String date = inputString();
					
					printTaskByDate(date);
						 
						}
				
				else  if(choose2 == 2){
				System.out.println("Write project's ID number:");
				String id = inputString();
				
				printTaskByProject(id);
				}
				System.out.println();
				printQuitOrNo();
				
				break;
			case 2:
				
				System.out.println("Write task's ID number");
				
				String IDNumber = inputString();
				while(findTask(IDNumber) != null) {
					System.out.println("Task with ID number " + IDNumber + " already exists!");
					System.out.println("Write other number: ");
					IDNumber = inputString();
				}
				
				System.out.println("Write task's title:");
				String title = inputString();
				
				System.out.println("Write task's date DDMMYY");
				String date2 = inputString();
				
				System.out.println("New project or current project? N/C");
				char NewOrCurrent = scan.nextLine().charAt(0);
				
				if (NewOrCurrent == 'N' || NewOrCurrent == 'n') {
					System.out.println("Write project id: ");
					
					String ID = inputString();
					
					String status = "Undone";
					Task task = new Task(IDNumber, title, date2, status, ID );
					tasks.add(task);
					
					System.out.println("You have added task to a project: " + ID);
				}
				else if(NewOrCurrent == 'C' || NewOrCurrent == 'c') {
					System.out.println("Write project id: ");
					String ID = inputString();
					
					String status = "Undone";
					Task task = new Task(IDNumber, title, date2, status, ID );
					tasks.add(task);
					
					
					System.out.println("You have added one task to a project: " + ID);
					}
				
				else {
					System.out.println("Invalid input: You have just to choses N or C");
				}
				System.out.println();
				printQuitOrNo();
				
				break;
			case 3:
				System.out.println("Write task's ID: ");
				String taskID = inputString();
				
				Task task = findTask(taskID);
				if (task == null) {
					System.out.println("Such task doesn't exists. Write again: ");
					taskID = inputString();
				}
				printChangeMenu();
				
				while (ready == false ) {
					int choice = inputInt();
					
					switch(choice) {
					case 1:
						System.out.println("Write new title: ");
						String newTitle = inputString();
						
						task.setTitle(newTitle);
						System.out.println("The title has changed!");
						
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						
						break;
					case 2:
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
						System.out.println("Write new date DDMMYY: ");
						String newDate = inputString();
						
						task.setDate(newDate);
						System.out.println("The date has changed!");
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						break;
					case 4:
						removeTask(taskID);
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						break;
					case 5:
						System.out.println("Write new task id: ");
						String newID = inputString();
						task.setNumber(newID);
						
						System.out.println();
						printQuitOrNoForSecondLoop();
						break;
					case 6:
						ready = true;
						printWelcome();
						break;
					
			}
						
			 }
				break;
				
				
			case 4:
				
				String text = arrayToString(tasks);
				
				writeTofile(text);
				printList(); // print content
				finished = true;
				System.out.println();
				System.out.println("Goodbye!");
				
				
		}

		
			}
		}
		


}
