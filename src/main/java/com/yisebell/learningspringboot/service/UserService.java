package com.yisebell.learningspringboot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yisebell.learningspringboot.dao.UserDao;
import com.yisebell.learningspringboot.model.User;

@Service
public class UserService {
	
	private UserDao userDao;

	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public Optional<User> getUser(UUID userUid) {
		return userDao.getUser(userUid);
	}

	public int updateUser(User user) {
		Optional<User> optionalUser = getUser(user.getUserUid());
		if(optionalUser.isPresent()) {
			return userDao.updateUser(user);
		}
		return -1;
	}

	public int removeUser(UUID userUid) {
		Optional<User> optionalUser = getUser(userUid);
		if(optionalUser.isPresent()) {
			return userDao.removeUser(userUid);
		}
		return -1;
	}

	public int insertUser(User user) {
		UUID uuid = UUID.randomUUID();
		user.setUserUid(uuid);
		return userDao.insertUser(uuid, user);
	}
}
