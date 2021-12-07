package org.rygn.kanban.controller;

import java.util.Collection;

import org.rygn.kanban.domain.Task;
import org.rygn.kanban.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public
class TaskController {

  private final TaskService service;

  TaskController(TaskService service) {
    this.service = service;
  }


  @GetMapping(path="/tasks")
  Collection<Task> getAllTasks() {
    return service.findAllTasks();
  }
  
  @PostMapping(value="/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Task> createTask(@RequestBody Task newTask) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createTask(newTask));
  }
  
  @PatchMapping("/tasks/{id}")
  Task moveTask(@RequestBody Boolean direction, @PathVariable Long id) {
    Task task = service.findTask(id);
    if(direction) {
    	return service.moveRightTask(task);
    }
    
    return service.moveLeftTask(task);
    
  }


  
}
