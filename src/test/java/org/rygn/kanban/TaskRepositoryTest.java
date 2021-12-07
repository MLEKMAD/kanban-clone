package org.rygn.kanban;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rygn.kanban.dao.TaskRepository;
import org.rygn.kanban.dao.TaskStatusRepository;
import org.rygn.kanban.dao.TaskTypeRepository;
import org.rygn.kanban.domain.Task;
import org.rygn.kanban.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class TaskRepositoryTest {

	@Autowired
    private TaskRepository taskRepository;
	
	@Autowired
    private TaskTypeRepository taskTypeRepository;
	
	@Autowired
    private TaskStatusRepository taskStatusRepository;
	
	@Test
	public void testFindAllTasks() {
		
		Collection<Task> tasks = this.taskRepository.findAll();
		
		Assert.assertEquals(1, tasks.size());
	}
	
	@Test
	public void testSaveTask() {
		
		Task task = new Task();
		task.setCreated(LocalDate.now());
		task.setTitle("task2");
		task.setNbHoursForecast(0);
		task.setNbHoursReal(0);

		task.setType(this.taskTypeRepository.findById(Constants.TASK_TYPE_FEATURE_ID).orElse(null));
		task.setStatus(this.taskStatusRepository.findById(Constants.TASK_STATUS_TODO_ID).orElse(null));
		
		this.taskRepository.save(task);
		
		Collection<Task> tasks = this.taskRepository.findAll();
		
		Assert.assertEquals(2, tasks.size());
		
		this.taskRepository.delete(task);
	}
}
