package com.revature.data.services;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Photos;

public class PhotoDAO implements PhotoDAOManager {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void create(Photos photo) {
		sessionFactory.getCurrentSession().save(photo);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public Photos findByPhotoId(int photoId) {
		return (Photos) sessionFactory.getCurrentSession().createCriteria(Photos.class).
				add(Restrictions.eq("photoId", photoId)).uniqueResult();
	}

	public void destroy(Photos photo) {
		sessionFactory.getCurrentSession().delete(photo);
	}

	
}