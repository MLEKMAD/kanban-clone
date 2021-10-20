package com.telecom.kanban.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ChangeLog {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date occuredAt;
	
	private Task task;
	private TaskStatus sourceStatus;
	private TaskStatus targetStatus;


	
	
}
