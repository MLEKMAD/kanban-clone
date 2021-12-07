package org.rygn.kanban.dao;

import org.rygn.kanban.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
