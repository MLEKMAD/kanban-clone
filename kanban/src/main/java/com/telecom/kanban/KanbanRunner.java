package com.telecom.kanban;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.telecom.kanban.model.Developer;
import com.telecom.kanban.repository.DeveloperRepo;
import com.telecom.kanban.repository.TaskRepo;

@Component
public class KanbanRunner implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(KanbanRunner.class);
	
	@Autowired
	private DeveloperRepo developerRepo;
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Developer dev = new Developer();
		dev.setEmail("med@med.com");
		dev.setLastName("lek");
		dev.setFirstName("med");
		dev.setPassword("toto");
		developerRepo.save(dev);		
	}

	
	
}
