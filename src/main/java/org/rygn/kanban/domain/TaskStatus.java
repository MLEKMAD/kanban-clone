package org.rygn.kanban.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TaskStatus {

	private @Id Long id;
	
	private String label;
	
	public TaskStatus(Long id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public TaskStatus() {		
	}
}
