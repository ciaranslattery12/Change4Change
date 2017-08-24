package com.revature.beans;

import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@Column(nullable=false, name="USER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL")
	private String email;
	
	@OneToOne
	@JoinColumn(nullable=false, name="ROLE_ID")
	private UserRole userRole;
	
	@ManyToMany
	@JoinTable(name="REGISTERED",
	joinColumns=@JoinColumn(name="REGISTERED_EVENT_ID", referencedColumnName="EVENT_ID"),
	inverseJoinColumns=@JoinColumn(name="REGISTERED_USER_ID", referencedColumnName="USER_ID"))
	private Set<Events> events;
}
