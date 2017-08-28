package com.revature.data.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Users;

public class UserDAO implements UserDAOManager {

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

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public Users findById(int userId){
		return (Users) sessionFactory.getCurrentSession().createCriteria(Users.class).
				add(Restrictions.eq("usersId", userId)).uniqueResult();
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	@SuppressWarnings("unchecked")
	public List<Users> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM Users").list();
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public Users findByEmail(String email) {
		return (Users) sessionFactory.getCurrentSession().createCriteria(Users.class).
				add(Restrictions.eq("email", email)).uniqueResult();
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public Users findByUserName(String userName) {
		return (Users) sessionFactory.getCurrentSession().createCriteria(Users.class).
				add(Restrictions.eq("userName", userName)).uniqueResult();	}
}
