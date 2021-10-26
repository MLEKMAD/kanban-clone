package com.telecom.kanban.service.impl;

import java.util.Collection;
import java.util.Optional;

import com.telecom.kanban.model.Task;
import com.telecom.kanban.model.TaskStatus;
import com.telecom.kanban.repository.TaskRepo;
import com.telecom.kanban.repository.TaskStatusRepo;
import com.telecom.kanban.service.TaskService;

public class TaskServiceImpl implements TaskService {

	private TaskRepo taskRepo;
	private TaskStatusRepo taskstatusRepo;
	@Override
	public Collection<Task> findAllTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Optional<Task> findTask(Long id) {
		return taskRepo.findById(id);
	}

	@Override
	public Task moveRightTask(Task task) {
		TaskStatus taskStatus = task.getTaskStatus();
		return null;
	}

	@Override
	public Task moveLeftTask(Task task) {
		TaskStatus taskStatus = task.getTaskStatus();
		return null;
	}

}
