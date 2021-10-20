package com.telecom.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.kanban.model.Developer;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer, Long> {

}
