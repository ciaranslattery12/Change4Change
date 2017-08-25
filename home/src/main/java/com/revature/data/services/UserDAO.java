package com.revature.data.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Users;

public class UserDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, 
			propagation = Propagation.REQUIRED, 
			rollbackFor = Exception.class)
	public void create(Users user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, 
			propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void update(Users user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, 
			propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void delete(Users user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	public Users findById(int userId){
		String queryString = "FROM Users WHERE userId = :userId";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setInteger("userId", userId);
		Object result = query.uniqueResult();
		return (Users)result;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Users> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM Users").list();
	}
}
