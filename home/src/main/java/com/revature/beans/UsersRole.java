package com.revature.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS_ROLE")
public class UsersRole {

	@Id
	@Column(name="USER_ROLE_ID", nullable=false)
	private int userRoleId;
	
	@Column(name="USER_ROLE_TYPE", nullable=false)
	private String userRoleType;

	public UsersRole() {
		super();
	}

	
	public UsersRole(int userRoleId) {
		super();
		this.userRoleId = userRoleId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userRoleId;
		result = prime * result + ((userRoleType == null) ? 0 : userRoleType.hashCode());
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
		UsersRole other = (UsersRole) obj;
		if (userRoleId != other.userRoleId)
			return false;
		if (userRoleType == null) {
			if (other.userRoleType != null)
				return false;
		} else if (!userRoleType.equals(other.userRoleType))
			return false;
		return true;
	}	
	
	
}
