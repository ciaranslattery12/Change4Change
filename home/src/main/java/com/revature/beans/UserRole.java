package com.revature.beans;

import javax.persistence.*;

@Entity
public class UserRole {

	@Id
	@Column(name="USER_ROLE_ID", nullable=false)
	private int userRoleId;
	
	@Column(name="USER_ROLE_TYPE", nullable=false)
	private String userRoleType;
	
	@OneToOne(mappedBy="userRoleId")
	private User user;
}
