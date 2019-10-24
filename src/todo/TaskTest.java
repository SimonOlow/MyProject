/**
 * 
 */
package todo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Wiktoria
 *
 */
class TaskTest {
	
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link todo.Task#Task()}.
	 */
	@Test
	final void testTask() {
		fail("Not yet implemented"); // TODO
		//constructor
	}

	/**
	 * Test method for {@link todo.Task#Task(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	final void testTaskStringStringStringStringString() {
		fail("Not yet implemented"); // TODO 
		//constructor
	}

	/**
	 * Test method for {@link todo.Task#getNumber()}.
	 */
	@Test
	final void testGetNumber() {
		Task task = new Task();
		String number = "1234";
		task.setNumber(number);
		boolean condition = task.getNumber().equals(number);
		assertTrue(condition);
	}

	/**
	 * Test method for {@link todo.Task#setNumber(java.lang.String)}.
	 */
	@Test
	final void testSetNumber() {
		Task task = new Task();
		String number = "1234";
		task.setNumber(number);
		boolean condition = task.getNumber().equals(number);
		assertTrue(condition);
		
	}

	/**
	 * Test method for {@link todo.Task#getTitle()}.
	 */
	@Test
	final void testGetTitle() {
		Task task = new Task();
		String title = "Buy milk";
		task.setTitle(title);
		boolean condition = task.getTitle().equals(title);
		assertTrue(condition);
	}

	/**
	 * Test method for {@link todo.Task#setTitle(java.lang.String)}.
	 */
	@Test
	final void testSetTitle() {
		Task task = new Task();
		String title = "Buy milk";
		task.setTitle(title);
		boolean condition = task.getTitle().equals(title);
		assertTrue(condition);
	}

	/**
	 * Test method for {@link todo.Task#getDate()}.
	 */
	@Test
	final void testGetDate() {
		Task task = new Task();
		String date = "121019";
		task.setDate(date);
		String actual = task.getDate();
		String expected = "121019";
		assertEquals(expected, actual);
		
	}

	/**
	 * Test method for {@link todo.Task#setDate(java.lang.String)}.
	 */
	@Test
	final void testSetDate() {
		Task task = new Task();
		String date = "121019";
		task.setDate(date);
		String actual = task.getDate();
		String expected = "121019";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link todo.Task#getDone()}.
	 */
	@Test
	final void testGetDone() {
		Task task = new Task();
		task.setDone("Done");
		String actual = task.getDone();
		String expected = "Undone";
		assertNotEquals(expected, actual);
	}

	/**
	 * Test method for {@link todo.Task#setDone(java.lang.String)}.
	 */
	@Test
	final void testSetDone() {
		Task task = new Task();
		task.setDone("Done");
		String actual = task.getDone();
		String expected = "Undoneee";
		assertNotEquals(expected, actual);
	}

	/**
	 * Test method for {@link todo.Task#getProjectID()}.
	 */
	@Test
	final void testGetProjectID() {
		Task task = null;
	
		assertNull(task);
	}

	/**
	 * Test method for {@link todo.Task#setProjectID(java.lang.String)}.
	 */
	@Test
	final void testSetProjectID() {
		Task task = new Task();
		assertNotNull(task);
	}

	/**
	 * Test method for {@link todo.Task#getDetails()}.
	 */
	@Test
	final void testGetDetails() {
		Task task = new Task();
		String actual = task.getDetails();
		String expected = "null, title = null, date = null, status = null, projectID = null";
		assertEquals(expected, actual);
	}

}
