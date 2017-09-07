package com.revature.beans;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PHOTOS")
public class Photos {

	@Id
	@Column(nullable=false, name="PHOTO_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int photoId;
	
	@Column(name="PHOTO_IMAGE")
	@Lob
	private byte[] image;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name="PHOTO_AUTHOR")
	@JsonIgnore
	private Events event;

	public Photos() {
		super();
	}
	
	public Photos(byte[] image){
		this.image = image;
	}
	
	public Photos(int photoId, byte[] image, Events event) {
		super();
		this.photoId = photoId;
		this.image = image;
		this.event = event;
	}

	public Photos(byte[] image, Events event) {
		super();
		this.image = image;
		this.event = event;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Photos [photoId=" + photoId + ", image=" + Arrays.toString(image) + ", event=" + event + "]";
	}
	
	
}
