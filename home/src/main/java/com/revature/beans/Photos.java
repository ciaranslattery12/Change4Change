package com.revature.beans;

import java.sql.Blob;

import javax.persistence.*;

@Entity
@Table(name="PHOTOS")
public class Photos {

	@Id
	@Column(nullable=false, name="PHOTO_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int photoId;
	
	@Column(name="PHOTO_IMAGE")
	private byte[] image;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="PHOTO_AUTHOR")
	private Events event;

	public Photos() {
		super();
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
	
	
}
