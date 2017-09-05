package com.revature.beans;

import java.util.Set;

import javax.persistence.*;

//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;

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
	
	@Column(name="USERNAME", nullable=false, unique=true)
	private String userName;
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Column(name="EMAIL", nullable=false, unique=true)
	private String email;
	
	@Column(name="USERS_BIO")
	private String usersBio;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private UsersRole userRole;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(name="SUBSCRIBERS",
	joinColumns=@JoinColumn(name="SUBSCRIBER_ID", referencedColumnName="USERS_ID"),
	inverseJoinColumns=@JoinColumn(name="EVENT_NUMBER", referencedColumnName="EVENT_ID"))
	private Set<Events> events;
	
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Events> ownedEvents;

	public Users() {
		super();
	}
	

	public Users(String firstName, String lastName, String userName, String password, String email, String usersBio,
			UsersRole userRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.usersBio = usersBio;
		this.userRole = userRole;
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
		this.userRole = userRoleId;
	}

	public Users(int usersId, String firstName, String lastName, Set<Events> events, Set<Events> ownedEvents, UsersRole userRole) {
		super();
		this.usersId = usersId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.events = events;
		this.ownedEvents = ownedEvents;
		this.userRole = userRole;
	}


	public Users(String firstName, String lastName, String userName, String password, String email,
			UsersRole userRoleId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userRole = userRoleId;
	}
	
	public Users(String firstName, String lastName, String userName, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
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
		return userRole;
	}

	public void setUserRoleId(UsersRole userRoleId) {
		this.userRole = userRoleId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		result = prime * result + usersId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		if (usersId != other.usersId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Users [usersId=" + usersId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", email=" + email + ", usersBio=" + usersBio + ", userRole="
				+ userRole + ", events=" + events + ", ownedEvents=" + ownedEvents + "]";
	}
	
	
}
