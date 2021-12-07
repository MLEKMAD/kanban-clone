package org.rygn.kanban.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Task {

	private @Id @GeneratedValue Long id;
	
	@NotBlank(message = "Title can not be empty")
	private String title;
	
	@NotNull(message = "nbHoursForecast can not be null")
	private Integer nbHoursForecast;
	
	@NotNull(message = "nbHoursReal can not be null")
	private Integer nbHoursReal;
	
	@NotNull(message = "nbHoursForecast can not be null")
	private LocalDate created;
	
	@ManyToOne
	@NotBlank(message = "Type can not be empty")
	private TaskType type;
	
	@ManyToOne
	private TaskStatus status;	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonBackReference
	@NotEmpty(message = "Developers can not be empty")
    private Set<Developer> developers;
	
	@OneToMany(mappedBy="task", cascade={CascadeType.ALL}, orphanRemoval=true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties(ignoreUnknown = true)
	private Set<ChangeLog> changeLogs;
	
	public Task() {
		
		this.developers = new HashSet<>();
		
		this.changeLogs = new HashSet<>();
	}
	
	public void addDeveloper(Developer developer) {
		
		developer.getTasks().add(this);
		
		this.developers.add(developer);
	}
	
	public void addChangeLog(ChangeLog changeLog) {
		
		changeLog.setTask(this);
		
		this.changeLogs.add(changeLog);
	}

	public void clearChangeLogs() {
		
		for (ChangeLog changeLog :  this.changeLogs) {
			
			changeLog.setTask(null);
		}
		
		this.changeLogs.clear();
	}
}
