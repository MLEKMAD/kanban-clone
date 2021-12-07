package org.rygn.kanban;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.domain.Task;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class TaskTest {
	
	@Test
	public void testAddDeveloperToTask() {
		
		Developer developer = new Developer();
		
		Task task = new Task();
		
		task.addDeveloper(developer);
		
		Assert.assertEquals(1, task.getDevelopers().size());
		
		Assert.assertEquals(1, developer.getTasks().size());
	}	
}
