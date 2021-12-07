package org.rygn.kanban.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TaskType {

	private @Id Long id;
	
	private String label;
	
	public TaskType(Long id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public TaskType() {
	}
}
