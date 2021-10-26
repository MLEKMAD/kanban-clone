package com.telecom.kanban.repoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.kanban.KanbanRunner;
import com.telecom.kanban.model.Developer;
import com.telecom.kanban.repository.DeveloperRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestDevRepo {

	@Autowired
	private DeveloperRepo devRepo;

	@Test
	void testFindAll() {
		List<Developer> allDevs = devRepo.findAll();
		assertEquals(allDevs.size(), 1);
	}

}
