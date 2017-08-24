package com.revature.beans;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Events {
	
	@Id
	@JoinColumn(nullable=false, name="EVENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventId;
	
	@Column(name="MAX_CAPACITY")
	private int maxCapacity;
	
	@Column(name="EVENT_DATE")
	private Timestamp eventDate;
	
	@ManyToMany(mappedBy="events")
	private Set<User> users;
	
	@OneToOne
	@JoinColumn(name="EVENT_TYPE_ID", nullable=false)
	private EventType eventType;
	
}