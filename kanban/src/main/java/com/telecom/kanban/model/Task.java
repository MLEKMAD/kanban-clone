package com.telecom.kanban.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private Integer nbHourForCast;
	private Integer nbHoursReal;
	private Date createdAt;
	private TaskType taskType;
	private TaskStatus taskStatus;
	
}
