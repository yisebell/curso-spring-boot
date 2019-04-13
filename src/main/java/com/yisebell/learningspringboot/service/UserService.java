package com.yisebell.learningspringboot.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yisebell.learningspringboot.dao.UserDao;
import com.yisebell.learningspringboot.model.User;
import com.yisebell.learningspringboot.model.User.Gender;

@Service
public class UserService {
	
	private UserDao userDao;

	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUsers(Optional<String> gender) {
		List<User> users = userDao.getAllUsers();
		if(!gender.isPresent()) {
			return userDao.getAllUsers();
		}
		try {
			Gender theGender = Gender.valueOf(gender.get().toUpperCase());
			return users.stream()
					.filter(user -> user.getGender().equals(theGender))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new IllegalStateException("Invalid gender",e);
		}
		
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
		UUID userUid = user.getUserUid() == null ? UUID.randomUUID() : user.getUserUid();
		return userDao.insertUser(userUid, User.newUser(userUid, user));
	}

	private void validateUser(User user) {
		Objects.requireNonNull(user.getFirstName(), "first name required");
		Objects.requireNonNull(user.getLastName(), "last name required");
		Objects.requireNonNull(user.getAge(), "age required");
		Objects.requireNonNull(user.getEmail(), "email required");
		// validate email		
		Objects.requireNonNull(user.getGender(), "gender required");
	}
}
