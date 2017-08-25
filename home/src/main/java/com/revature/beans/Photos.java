package com.revature.beans;

import java.sql.Blob;

import javax.persistence.*;

@Entity
@Table(name="PHOTOS")
public class Photos {

	@Id
	@JoinColumn(nullable=false, name="PHOTO_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int photoId;
	
	@Column(name="PHOTO_IMAGE")
	private Blob photoBlob;
	
	@ManyToOne
	@JoinColumn(name="PHOTO_AUTHOR")
	private Events event;

	public Photos() {
		super();
	}

	public Photos(int photoId, Blob photoBlob, Events event) {
		super();
		this.photoId = photoId;
		this.photoBlob = photoBlob;
		this.event = event;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public Blob getPhotoBlob() {
		return photoBlob;
	}

	public void setPhotoBlob(Blob photoBlob) {
		this.photoBlob = photoBlob;
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}
	
	
}
