package com.revature.data.services;

import com.revature.beans.Photos;

public interface PhotoDAOManager {
	
	public void create(Photos photo);
	public Photos findByPhotoId(int photoId);
	public void destroy(Photos photo);
}
