package com.revature.beans;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="USERS")
public class Users {

	@Id
	@Column(nullable=false, name="USERS_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;
	
	@Column(name="USERNAME", nullable=false)
	private String userName;
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	@Column(name="USERS_BIO")
	private String usersBio;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="ROLE_ID")
	private UsersRole userRoleId;
	
	@ManyToMany
	@JoinTable(name="SUBSCRIBERS",
	joinColumns=@JoinColumn(name="EVENT_NUMBER", referencedColumnName="EVENT_ID"),
	inverseJoinColumns=@JoinColumn(name="SUBSCRIBER_ID", referencedColumnName="USER_ID"))
	private Set<Events> events;
}
