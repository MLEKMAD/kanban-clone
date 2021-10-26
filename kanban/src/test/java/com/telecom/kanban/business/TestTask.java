package com.telecom.kanban.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.telecom.kanban.model.Developer;
import com.telecom.kanban.model.Task;

class TestTask {

	@Test
	void testAddDeveloper() {
		Developer dev = new Developer();
		dev.setEmail("med@med.com");
		dev.setLastName("lek");
		dev.setFirstName("med");
		dev.setPassword("toto");
		Task task = new Task();
		task.setTitle("my Task");
		task.addDeveloper(dev);
		Developer toTestWith = task.getDevelopers().get(task.getDevelopers().size() -1);
		assertEquals(toTestWith.getLastName(), dev.getLastName());
		assertEquals(toTestWith.getFirstName(), dev.getFirstName());
		assertEquals(toTestWith.getEmail(), dev.getEmail());

	}

}
