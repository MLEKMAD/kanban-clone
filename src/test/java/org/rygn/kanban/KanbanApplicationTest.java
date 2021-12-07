package org.rygn.kanban;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rygn.kanban.dao.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class KanbanApplicationTest {

	@Autowired
	private TaskRepository taskRepository;
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(taskRepository).isNotNull();
	}
}
