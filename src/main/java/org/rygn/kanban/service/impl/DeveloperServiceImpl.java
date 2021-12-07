package org.rygn.kanban.service.impl;

import java.util.List;

import org.rygn.kanban.dao.DeveloperRepository;
import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperServiceImpl implements DeveloperService {

	@Autowired
	private DeveloperRepository developerRepository;
	
	public List<Developer> findAllDevelopers() {
		
		return this.developerRepository.findAll();
	}
}
