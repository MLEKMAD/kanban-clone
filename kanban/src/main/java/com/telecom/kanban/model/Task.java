package com.telecom.kanban.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
	@ManyToOne
	private TaskType taskType;
	@ManyToOne
	private TaskStatus taskStatus;
	
	@ManyToMany(mappedBy = "tasks")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Developer> developers = new ArrayList<Developer>();
	@OneToMany(mappedBy = "task")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<ChangeLog> changeLogs = new ArrayList<ChangeLog>();
	
	public void addDeveloper(Developer dev) {
		this.developers.add(dev);
	}

}
