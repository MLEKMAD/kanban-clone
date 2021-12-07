package org.rygn.kanban;

import org.rygn.kanban.domain.ChangeLog;
import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.domain.Task;
import org.rygn.kanban.domain.TaskStatus;
import org.rygn.kanban.domain.TaskType;
import org.rygn.kanban.service.DeveloperService;
import org.rygn.kanban.service.TaskService;
import org.rygn.kanban.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class TaskServiceTest {

	@Autowired
    private TaskService taskService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Test
	public void testFindAllTasks() {
		
		Collection<Task> tasks = this.taskService.findAllTasks();
		
		Assert.assertEquals(1, tasks.size());
	}
	
	@Test
	public void testFindAllTaskTypes() {
		
		Collection<TaskType> taskTypes = this.taskService.findAllTaskTypes();
		
		Assert.assertEquals(2, taskTypes.size());
	}
	
	@Test
	public void testFindAllTaskStatus() {
		
		Collection<TaskStatus> taskStatus = this.taskService.findAllTaskStatus();
		
		Assert.assertEquals(4, taskStatus.size());
	}
	
	@Test
	public void testChangeTaskStatus() {
		
		Task task = this.taskService.findAllTasks().iterator().next();
		
		TaskStatus status1 = this.taskService.findTaskStatus(1L);
		
		TaskStatus status2 = this.taskService.findTaskStatus(2L);
		
		task = this.taskService.changeTaskStatus(task, status2);
		
		Assert.assertEquals(status2, task.getStatus());
		
		Collection<ChangeLog> changeLogs = this.taskService.findChangeLogsForTask(task);
		
		Assert.assertEquals(1, changeLogs.size());
		
		ChangeLog changeLog = changeLogs.iterator().next();
		
		Assert.assertEquals(status1, changeLog.getSourceStatus());
		
		Assert.assertEquals(status2, changeLog.getTargetStatus());		
	}
	
	@Test
	public void testDisplayMoveRightForTask() {
		
		TaskStatus todoStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus testStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TEST_ID);
		
		TaskStatus doneStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		Task task = new Task();
		
		task.setStatus(todoStatus);
		
		Assert.assertTrue(this.taskService.displayMoveRightForTask(task));
		
		task.setStatus(doingStatus);
		
		Assert.assertTrue(this.taskService.displayMoveRightForTask(task));
		
		task.setStatus(testStatus);
		
		Assert.assertTrue(this.taskService.displayMoveRightForTask(task));
		
		task.setStatus(doneStatus);
		
		Assert.assertFalse(this.taskService.displayMoveRightForTask(task));
	}
	
	@Test
	public void testDisplayMoveLeftForTask() {
		
		TaskStatus todoStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus testStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TEST_ID);
		
		TaskStatus doneStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		Task task = new Task();
		
		task.setStatus(todoStatus);
		
		Assert.assertFalse(this.taskService.displayMoveLeftForTask(task));
		
		task.setStatus(doingStatus);
		
		Assert.assertTrue(this.taskService.displayMoveLeftForTask(task));
		
		task.setStatus(testStatus);
		
		Assert.assertTrue(this.taskService.displayMoveLeftForTask(task));
		
		task.setStatus(doneStatus);
		
		Assert.assertTrue(this.taskService.displayMoveLeftForTask(task));
	}
	
	@Test
	public void testMoveRightTask() {
		
		Developer developer = this.developerService.findAllDevelopers().iterator().next();
		
		TaskStatus todoStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus testStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TEST_ID);
		
		TaskStatus doneStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		Task task = new Task();
		task.setNbHoursForecast(0);
		task.setNbHoursReal(0);
		task.setTitle("title");
		task.setStatus(todoStatus);
		task.addDeveloper(developer);
		
		task = this.taskService.createTask(task);
		
		task = this.taskService.moveRightTask(task);
		
		Assert.assertEquals(doingStatus, task.getStatus());
		
		Collection<ChangeLog> changeLogs = this.taskService.findChangeLogsForTask(task);
		
		Assert.assertEquals(1, changeLogs.size());
		
		ChangeLog changeLog = changeLogs.iterator().next();
		
		Assert.assertEquals(todoStatus, changeLog.getSourceStatus());
		
		Assert.assertEquals(doingStatus, changeLog.getTargetStatus());
		
		task = this.taskService.moveRightTask(task);
		
		Assert.assertEquals(testStatus, task.getStatus());
		
		task = this.taskService.moveRightTask(task);
		
		Assert.assertEquals(doneStatus, task.getStatus());
		
		Assert.assertEquals(3, task.getChangeLogs().size());
		
		this.taskService.deleteTask(task);
	}
	
	@Test
	public void testMoveLeftTask() {
		
		Developer developer = this.developerService.findAllDevelopers().iterator().next();
				
		TaskStatus todoStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus testStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_TEST_ID);
		
		TaskStatus doneStatus = this.taskService.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		Task task = new Task();
		task.setNbHoursForecast(0);
		task.setNbHoursReal(0);
		task.setTitle("title");		
		task.addDeveloper(developer);
		task = this.taskService.createTask(task);
		
		task = this.taskService.moveRightTask(task); // => DOING
		task = this.taskService.moveRightTask(task); // => TEST
		task = this.taskService.moveRightTask(task); // => DONE
		
		task = this.taskService.moveLeftTask(task);
		
		Assert.assertEquals(testStatus, task.getStatus());
		
		Collection<ChangeLog> changeLogs = this.taskService.findChangeLogsForTask(task);
		
		Assert.assertEquals(4, changeLogs.size());
		
		boolean lastChangeLogFound = false;
		
		for (ChangeLog changeLog : changeLogs) {
		
			if (doneStatus.equals(changeLog.getSourceStatus())) {
				
				lastChangeLogFound = true;
				
				Assert.assertEquals(testStatus, changeLog.getTargetStatus());
			}
		}
		
		Assert.assertTrue(lastChangeLogFound);
		
		task = this.taskService.moveLeftTask(task);
		
		Assert.assertEquals(doingStatus, task.getStatus());
		
		task = this.taskService.moveLeftTask(task);
		
		Assert.assertEquals(todoStatus, task.getStatus());
		
		Assert.assertEquals(6, task.getChangeLogs().size());
		
		this.taskService.deleteTask(task);
	}
}
