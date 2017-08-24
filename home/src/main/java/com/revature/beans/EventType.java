package com.revature.beans;

import javax.persistence.*;

@Entity
public class EventType {

	@Id
	@Column(name="EVENT_TYPE_ID")
	private int eventTypeId;
	
	@Column(name="EVENT_TYPE_DESCRIPTION")
	private String eventTypeDescription;
	
}
