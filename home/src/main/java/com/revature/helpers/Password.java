package com.revature.helpers;

import org.mindrot.jbcrypt.BCrypt;

public class Password{
	//workload for jbcrypt, can be between 10-31
	private static int workload = 15;
	
	public static String hashPassword(String plainTextPassword){
		String salt = BCrypt.gensalt(workload);
		String hashedPassword = BCrypt.hashpw(plainTextPassword, salt);
		return (hashedPassword);	
	}
	
	public static boolean checkPassword(String input, String hashed){
		if(BCrypt.checkpw(input, hashed))
			return true;
		else
			return false;
	}
}