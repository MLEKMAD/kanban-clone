package com.telecom.kanban.service.impl;

import java.util.Collection;
import java.util.List;
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
		List<TaskStatus> allStatus = taskstatusRepo.findAll();
		TaskStatus taskStatus = task.getTaskStatus();
		for (TaskStatus status : allStatus) {
			if(taskStatus.getId() == status.getId()) {
				int index = allStatus.indexOf(status);
				if(index != allStatus.size()) task.setTaskStatus(allStatus.get(index+1));
			}
		}
		return task;
	}

	@Override
	public Task moveLeftTask(Task task) {
		List<TaskStatus> allStatus = taskstatusRepo.findAll();
		TaskStatus taskStatus = task.getTaskStatus();
		for (TaskStatus status : allStatus) {
			if(taskStatus.getId() == status.getId()) {
				int index = allStatus.indexOf(status);
				if(index != 0) task.setTaskStatus(allStatus.get(index-1));
			}
		}
		return task;
	}

}
