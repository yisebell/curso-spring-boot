package com.yisebell.learningspringboot.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.yisebell.learningspringboot.model.User;

public interface UserDao {
	
	List<User> getAllUsers();
	Optional<User> getUser(UUID userUid);
	int updateUser(User user);
	int removeUser(UUID userUid);
	int insertUser(UUID userUid,User user);
}
