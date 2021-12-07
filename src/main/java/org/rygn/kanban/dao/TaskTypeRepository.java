package org.rygn.kanban.dao;

import org.rygn.kanban.domain.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

}
