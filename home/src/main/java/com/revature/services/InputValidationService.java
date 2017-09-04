package com.revature.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

import com.revature.beans.EventStatus;
import com.revature.beans.Events;
import com.revature.beans.Users;

@Service
public class InputValidationService {

	private boolean signupInputValidated = false;
	private boolean eventInputValidated = false;
	private static String userNameAndPasswordRegex = "[a-zA-Z0-9_.!?]{4,20}";
	private static String nameRegex = "[a-zA-Z0-9']{1,25}";
	private static String emailRegex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	private static String eventTitleRegex = "[a-zA-Z0-9'_]{4,50}";
	private static String eventCapacityRegex = "^(?:[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$";
	private static String eventDateRegex = "\\d{1,2}/\\d{1,2}/\\d{4}";
	private static String eventDescriptionRegex = "[a-zA-Z0-9_.']{8,250}";
	
	public Users validateInput(Users user){
		String cleanUsername = Jsoup.clean(user.getUserName(), Whitelist.basic());
		String cleanPassword = Jsoup.clean(user.getPassword(), Whitelist.basic());
		String cleanFirstName = Jsoup.clean(user.getFirstName(), Whitelist.basic());
		String cleanLastName = Jsoup.clean(user.getLastName(), Whitelist.basic());
		String cleanEmail = Jsoup.clean(user.getEmail(), Whitelist.basic());
		if(cleanUsername.length() < 4 || cleanUsername.length() > 20 || 
				!cleanUsername.matches(userNameAndPasswordRegex)){
			return new Users();
		}else if(cleanPassword.length() < 8 || cleanPassword.length() > 20 ||
				!cleanPassword.matches(userNameAndPasswordRegex)){
			return new Users();
		}else if(cleanFirstName.length() < 1 || cleanFirstName.length() > 25 ||
				!cleanFirstName.matches(nameRegex)){
			return new Users();
		}else if(cleanLastName.length() < 1 || cleanLastName.length() > 25 ||
				!cleanLastName.matches(nameRegex)){
			return new Users();
		}else if(!cleanEmail.matches(emailRegex)){
			return new Users();
		}else{
			signupInputValidated = true;
			return new Users(cleanFirstName, cleanLastName, cleanUsername, cleanPassword, cleanEmail);
		}
	}
	
	public Events validateInput(Events event){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		Integer capacity = event.getMaxCapacity();
		String cleanEventTitle = Jsoup.clean(event.getEventTitle(), Whitelist.basic());
		String cleanEventDescription = Jsoup.clean(event.getEventDescription(), Whitelist.basic());
		String cleanMaxCapacity = Jsoup.clean(capacity.toString(), Whitelist.basic());
		String cleanStartTime = Jsoup.clean(dateFormat.format(event.getStartTime()), Whitelist.basic());
		String cleanEndTime = Jsoup.clean(dateFormat.format(event.getEndTime()), Whitelist.basic());
		if(cleanEventTitle.length() < 4 || cleanEventTitle.length() > 50 || 
				!cleanEventTitle.matches(eventTitleRegex)){
			return new Events();
		}else if(cleanEventDescription.length() < 8 || cleanEventDescription.length() > 250 || 
				!cleanEventDescription.matches(eventDescriptionRegex)){
			return new Events();
		}else if(Integer.parseInt(cleanMaxCapacity) < 1 || Integer.parseInt(cleanMaxCapacity) > 9999){
			return new Events();
		}else if(!cleanStartTime.matches(eventDateRegex)){
			return new Events();
		}else if(!cleanEndTime.matches(eventDateRegex)){
			return new Events();
		}else{
			eventInputValidated = true;
			return new Events(Integer.parseInt(cleanMaxCapacity), Date.valueOf(cleanStartTime), 
					Date.valueOf(cleanEndTime), cleanEventDescription, event.getEventType(),
					event.getUser(), new EventStatus(1, "UPCOMING"), cleanEventTitle);
		}

	}

	public boolean isSignupInputValidated() {
		return signupInputValidated;
	}

	public void setSignupInputValidated(boolean signupInputValidated) {
		this.signupInputValidated = signupInputValidated;
	}

	public boolean isEventInputValidated() {
		return eventInputValidated;
	}

	public void setEventInputValidated(boolean eventInputValidated) {
		this.eventInputValidated = eventInputValidated;
	}
	
}
