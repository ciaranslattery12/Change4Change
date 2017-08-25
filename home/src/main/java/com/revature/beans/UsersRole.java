package com.revature.beans;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS_ROLE")
public class UsersRole {
	HashMap<Integer, Integer> map;
	@Id
	@Column(name="USER_ROLE_ID", nullable=false)
	private int userRoleId;
	
	@Column(name="USER_ROLE_TYPE", nullable=false)
	private String userRoleType;
	
	@OneToMany(mappedBy="userRoleId")
	private Set<Users> users;
}
