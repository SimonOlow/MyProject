package todo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoListTest {

	TodoList todo = new TodoList();
	ArrayList<Task> tasks = new ArrayList<>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	final void testFindTaskbyDate() {
	String date = "121019";
	Task task = new Task();
	task = todo.findTaskbyDate(date);
	boolean condition = task == null;
	assertTrue(condition);
	
	}

	@Test
	final void testFindTask() {
		String ID = "1234";
		Task task = new Task();
		task = todo.findTaskbyDate(ID);
		boolean condition = task == null;
		assertTrue(condition);
	}


	@Test
	final void testHowManyUndone() {
		Task one = new Task();
		Task two = new Task();
		one.setDone("Done");
		two.setDone("Undone");
		tasks.add(one);
		tasks.add(two);
		int size = 0;
    	for (Task t : this.tasks) 
			if (t.getDone().equals("Undone")) 
				size ++;
    	int actual = size;
    	int expected = 1;
    	assertEquals(expected, actual);
	}

	@Test
	final void testHowManyDoneMethod() {
		Task one = new Task();
		Task two = new Task();
		Task three = new Task();
		one.setDone("Done");
		two.setDone("Undone");
		three.setDone("Done");
		tasks.add(one);
		tasks.add(two);
		tasks.add(three);
		int size = 0;
    	for (Task t : this.tasks) 
			if (t.getDone().equals("Done")) 
				size ++;
    	int actual = size;
    	int expected = 2;
    	assertEquals(expected, actual);
	}


}
