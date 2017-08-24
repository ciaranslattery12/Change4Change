package com.revature.beans;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserRole {
	HashMap<Integer, Integer> map;
	@Id
	@Column(name="USER_ROLE_ID", nullable=false)
	private int userRoleId;
	
	@Column(name="USER_ROLE_TYPE", nullable=false)
	private String userRoleType;
	
	@OneToOne(mappedBy="userRoleId")
	private User user;
}
