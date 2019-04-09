package com.yisebell.learningspringboot.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.yisebell.learningspringboot.model.User;
import com.yisebell.learningspringboot.model.User.Gender;


@Repository
public class FakeDataDao implements UserDao{

	private Map<UUID,User> database;
	
	public FakeDataDao() {
		database = new HashMap<>();
		UUID userUid = UUID.randomUUID();
		database.put(userUid, new User(userUid,"Joe","Jones",
				Gender.MALE,22,"joe@mail.com"));
	}
	
	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(database.values());
	}

	@Override
	public Optional<User> getUser(UUID userUid) {
		return Optional.ofNullable(database.get(userUid));
	}

	@Override
	public int updateUser(User user) {
		database.put(user.getUserUid(), user);
		return 0;
	}

	@Override
	public int removeUser(UUID userUid) {
		database.remove(userUid);
		return 0;
	}

	@Override
	public int insertUser(UUID userUid, User user) {
		database.put(userUid, user);
		return 0;
	}

}
