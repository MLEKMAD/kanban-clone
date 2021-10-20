package com.telecom.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.kanban.model.ChangeLog;

@Repository
public interface ChangeLogRepo extends JpaRepository<ChangeLog, Long> {

}
