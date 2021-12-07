package org.rygn.kanban.dao;

import org.rygn.kanban.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
