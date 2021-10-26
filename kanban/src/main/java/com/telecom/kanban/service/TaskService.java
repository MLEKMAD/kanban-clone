package com.telecom.kanban.service;

import java.util.Collection;
import java.util.Optional;

import com.telecom.kanban.model.Task;

public interface TaskService {

	public Collection<Task> findAllTasks();
	public Optional<Task> findTask(Long id);
	public Task moveRightTask(Task task);
	public Task moveLeftTask(Task task);
	
}
