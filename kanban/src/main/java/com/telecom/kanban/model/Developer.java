package com.telecom.kanban.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Developer {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private Date startContract;
	
	@NotBlank(message = "Email cannot be blank")
	@Column(unique = true)
	@Email(message = "Email")
	private String email;
	
	@ManyToMany()
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Task> tasks = new ArrayList<Task>();
	
	
}
