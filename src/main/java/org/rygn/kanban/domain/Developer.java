package org.rygn.kanban.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Developer {

	private @Id @GeneratedValue Long id;
	  
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String password;
	
	private LocalDate startContract;
	 
	@ManyToMany(mappedBy="developers", fetch=FetchType.EAGER)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
    @JsonBackReference
    @JsonIgnoreProperties(ignoreUnknown = true)
	//@JsonManagedReference
    private Set<Task> tasks;
	
	public Developer() {
		
		this.tasks = new HashSet<>();
	}
}
