package com.telecom.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.kanban.model.TaskType;

@Repository
public interface TaskTypeRepo extends JpaRepository<TaskType , Long> {

}
