package com.revature.beans;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="EVENTS")
public class Events {
	
	@Id
	@Column(nullable=false, name="EVENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventId;
	
	@Column(name="MAX_CAPACITY")
	private int maxCapacity;
	
	@Column(name="EVENT_DATE")
	private Timestamp eventDate;
	
	@Column(name="EVENT_DESCRIPTION")
	private String eventDescription;
	
	@ManyToMany(mappedBy="events", cascade=CascadeType.PERSIST)
	private Set<Users> users;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="EVENT_TYPE_ID", nullable=false)
	private EventType eventType;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="EVENT_AUTHOR", nullable=false)
	private Users user;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="EVENT_STATUS_ID", nullable=false)
	private EventStatus eventStatus;
	
	@OneToMany(mappedBy="event", cascade=CascadeType.ALL)
	private Set<Photos> photos;

	public Events() {
		super();
	}

	public Events(int maxCapacity, Timestamp eventDate, String eventDescription, Set<Users> users,
			EventType eventType, Users user, EventStatus eventStatus) {
		super();
		this.maxCapacity = maxCapacity;
		this.eventDate = eventDate;
		this.eventDescription = eventDescription;
		this.users = users;
		this.eventType = eventType;
		this.user = user;
		this.eventStatus = eventStatus;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	public Set<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photos> photos) {
		this.photos = photos;
	}
	
	
}
