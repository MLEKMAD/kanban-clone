package com.telecom.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.kanban.model.TaskStatus;

@Repository
public interface TaskStatusRepo extends JpaRepository<TaskStatus, Long> {

}
