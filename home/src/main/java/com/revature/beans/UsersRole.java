package com.revature.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS_ROLE")
public class UsersRole {

	@Id
	@Column(name="USER_ROLE_ID", nullable=false)
	private int userRoleId;
	
	@Column(name="USER_ROLE_TYPE", nullable=false)
	private String userRoleType;
	
	@OneToMany(mappedBy="userRoleId")
	private Set<Users> users;

	public UsersRole() {
		super();
	}

	public UsersRole(int userRoleId, String userRoleType) {
		super();
		this.userRoleId = userRoleId;
		this.userRoleType = userRoleType;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleType() {
		return userRoleType;
	}

	public void setUserRoleType(String userRoleType) {
		this.userRoleType = userRoleType;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
	
}
