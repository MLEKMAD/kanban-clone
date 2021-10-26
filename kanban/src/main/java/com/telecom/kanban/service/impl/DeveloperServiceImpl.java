package com.telecom.kanban.service.impl;

import java.util.List;

import com.telecom.kanban.model.Developer;
import com.telecom.kanban.repository.DeveloperRepo;
import com.telecom.kanban.service.DeveloperService;

public class DeveloperServiceImpl implements DeveloperService {
	
	private DeveloperRepo devRepo;

	@Override
	public List<Developer> findAllDevelopers() {
		return devRepo.findAll();
	}
	
	

}
