package org.rygn.kanban.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import org.hibernate.Hibernate;
import org.rygn.kanban.dao.TaskRepository;
import org.rygn.kanban.dao.TaskStatusRepository;
import org.rygn.kanban.dao.TaskTypeRepository;
import org.rygn.kanban.domain.ChangeLog;
import org.rygn.kanban.domain.Task;
import org.rygn.kanban.domain.TaskStatus;
import org.rygn.kanban.domain.TaskType;
import org.rygn.kanban.service.TaskService;
import org.rygn.kanban.utils.Constants;
import org.rygn.kanban.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	@Autowired
	private TaskTypeRepository taskTypeRepository;
		
	@Override
	@Transactional(readOnly = true)
	public Collection<Task> findAllTasks() {
		log.info("All Tasks:" + this.taskRepository.findAll().toString());
		return this.taskRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<TaskType> findAllTaskTypes() {
		
		return this.taskTypeRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<TaskStatus> findAllTaskStatus() {
		
		return this.taskStatusRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Task findTask(Long id) {
		
		return this.taskRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TaskStatus findTaskStatus(Long id) {
		
		return this.taskStatusRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TaskType findTaskType(Long id) {
		
		return this.taskTypeRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<ChangeLog> findChangeLogsForTask(Task task) {
		
		Task foundTask = this.findTask(task.getId());
		
		if (foundTask != null) {
			
			// force initialization
			Hibernate.initialize(foundTask.getChangeLogs());
			
			return foundTask.getChangeLogs();
		}
		else {
			return new HashSet<>();
		}
	}

	@Override
	@Transactional
	public Task changeTaskStatus(Task task, TaskStatus targetStatus) {

		task = this.taskRepository.save(task);
		
		ChangeLog changeLog = new ChangeLog();
		changeLog.setOccured(LocalDateTime.now());
		changeLog.setSourceStatus(task.getStatus());
		changeLog.setTargetStatus(targetStatus);
		
		task.addChangeLog(changeLog);
		
		task.setStatus(targetStatus);
		
		return task;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean displayMoveRightForTask(Task task) {
		
		TaskStatus done = this.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		return !task.getStatus().equals(done);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean displayMoveLeftForTask(Task task) {
		
		TaskStatus todo = this.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		return !task.getStatus().equals(todo);
	}

	@Override
	@Transactional
	public Task moveRightTask(Task task) {
		
		TaskStatus targetStatus = this.getTargetStatusForMoveRight(task.getStatus());
		
		return this.changeTaskStatus(task, targetStatus);
	}

	@Transactional(readOnly = true)
	private TaskStatus getTargetStatusForMoveRight(TaskStatus status) {
		
		TaskStatus todoStatus = this.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus testStatus = this.findTaskStatus(Constants.TASK_STATUS_TEST_ID);
		
		TaskStatus doneStatus = this.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		TaskStatus result = null;
		
		if (status != null) {
			
			if (status.equals(todoStatus)) {
				
				result = doingStatus;
			}
			else if (status.equals(doingStatus)) {
				
				result = testStatus;
			}
			else if (status.equals(testStatus)) {
				
				result = doneStatus;
			}
			else if (status.equals(doneStatus)) {
				
				throw new IllegalStateException();
			}
		}
		else {
			throw new IllegalArgumentException();
		}
		
		return result;
	}

	@Override
	@Transactional
	public Task moveLeftTask(Task task) {
		
		TaskStatus targetStatus = this.getTargetStatusForMoveLeft(task.getStatus());
		
		return this.changeTaskStatus(task, targetStatus);
	}

	@Transactional(readOnly = true)
	private TaskStatus getTargetStatusForMoveLeft(TaskStatus status) {
		
		TaskStatus todoStatus = this.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus testStatus = this.findTaskStatus(Constants.TASK_STATUS_TEST_ID);
		
		TaskStatus doneStatus = this.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		TaskStatus result = null;
		
		if (status != null) {
			
			if (status.equals(todoStatus)) {
				
				throw new IllegalStateException();
			}
			else if (status.equals(doingStatus)) {
				
				result = todoStatus;
			}
			else if (status.equals(testStatus)) {
				
				result = doingStatus;
			}
			else if (status.equals(doneStatus)) {
				
				result = testStatus;
			}
		}
		else {
			throw new IllegalArgumentException();
		}
		
		return result;
	}

	@Override
	@Transactional
	public Task createTask(Task task) {
		
		TaskStatus todo = this.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		task.setStatus(todo);
		
		task.setCreated(LocalDate.now());

		return this.taskRepository.save(task);
	}

	@Override
	@Transactional
	public void deleteTask(Task task) {
		
		task = this.taskRepository.save(task);
		
		task.clearChangeLogs();
		
		this.taskRepository.delete(task);
	}
}
