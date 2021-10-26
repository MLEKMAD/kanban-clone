package com.telecom.kanban.repoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.kanban.KanbanRunner;
import com.telecom.kanban.model.Developer;
import com.telecom.kanban.model.Task;
import com.telecom.kanban.repository.DeveloperRepo;
import com.telecom.kanban.repository.TaskRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestTaskRepo {

	@Autowired
	private TaskRepo taskRepo; 
	@Autowired
	private DeveloperRepo devRepo;
	@Test
	void testSave() {
		Task task = new Task();
		task.setNbHourForCast(2);
		task.setNbHoursReal(1);
		task.setTitle("Write services code");
		List<Developer> allDevs = devRepo.findAll();
		task.addDeveloper(allDevs.get(0));
		taskRepo.save(task);
		List<Task> allTasks = taskRepo.findByTitle("Write services code");
		assertNotEquals(allTasks.size(), 0);
		KanbanRunner.logger.debug("devs: " + allDevs.get(0).toString());

		
	}
	

}
