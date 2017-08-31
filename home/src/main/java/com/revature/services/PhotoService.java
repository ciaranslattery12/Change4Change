package com.revature.services;


import com.revature.beans.Photos;

public class PhotoService {
	
	//private Logger logger;
	private PhotoDAOManager photoDAOManager;
	public PhotoDAOManager getPhotoDAOManager() {
		return photoDAOManager;
	}
	public void setPhotoDAOManager(PhotoDAOManager photoDAOManager) {
		this.photoDAOManager = photoDAOManager;
	}

	public void createNewPhoto(Photos photo){
		//logger.debug("Created Photo: " + photo);
		photoDAOManager.create(photo);
	}
	
	public Photos findPhotoById(int photoId){
		//logger.debug("Finding Photo with id of: " + photoId);
		return photoDAOManager.findByPhotoId(photoId);
	}
	
	public void deletePhoto(Photos photo){
		//logger.debug("Deleting Photo: " + photo);
		photoDAOManager.destroy(photo);
	}
}
