package com.telecom.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.kanban.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

}
