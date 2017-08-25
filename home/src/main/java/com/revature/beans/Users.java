package com.revature.beans;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="USERS")
public class Users {

	@Id
	@Column(nullable=false, name="USERS_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int usersId;
	
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
	joinColumns=@JoinColumn(name="SUBSCRIBER_ID", referencedColumnName="USERS_ID"),
	inverseJoinColumns=@JoinColumn(name="EVENT_NUMBER", referencedColumnName="EVENT_ID"))
	private Set<Events> events;
	
	@OneToMany(mappedBy="user")
	private Set<Events> ownedEvents;

	public Users() {
		super();
	}

	public Users(int usersId, String firstName, String lastName, String userName, String password, String email,
			UsersRole userRoleId) {
		super();
		this.usersId = usersId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userRoleId = userRoleId;
	}
	
	

	public Users(String firstName, String lastName, String userName, String password, String email,
			UsersRole userRoleId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userRoleId = userRoleId;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsersBio() {
		return usersBio;
	}

	public void setUsersBio(String usersBio) {
		this.usersBio = usersBio;
	}

	public UsersRole getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(UsersRole userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

	public Set<Events> getOwnedEvents() {
		return ownedEvents;
	}

	public void setOwnedEvents(Set<Events> ownedEvents) {
		this.ownedEvents = ownedEvents;
	}
	
	
}
