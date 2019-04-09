/**
 * 
 */
package com.yisebell.learningspringboot.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.yisebell.learningspringboot.model.User;
import com.yisebell.learningspringboot.model.User.Gender;

/**
 * @author ljhidalgo
 *
 */
public class FakeDataDaoTest {
	
	private FakeDataDao fakeDataDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fakeDataDao = new FakeDataDao();
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.dao.FakeDataDao#getAllUsers()}.
	 */
	@Test
	public void testGetAllUsers() {
		List<User> users = fakeDataDao.getAllUsers();
		assertThat(users).hasSize(1);
		
		User user = users.get(0);
		
		assertThat(user.getAge()).isEqualTo(22);
		assertThat(user.getFirstName()).isEqualTo("Joe");
		assertThat(user.getLastName()).isEqualTo("Jones");
		assertThat(user.getGender()).isEqualTo(Gender.MALE);
		assertThat(user.getEmail()).isEqualTo("joe@mail.com");
		assertThat(user.getUserUid()).isNotNull();
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.dao.FakeDataDao#getUser(java.util.UUID)}.
	 */
	@Test
	public void testGetUser() {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		fakeDataDao.insertUser(annaUserUid, anna);
		assertThat(fakeDataDao.getAllUsers()).hasSize(2);
		Optional<User> annaOptional = fakeDataDao.getUser(annaUserUid);
		assertThat(annaOptional.isPresent()).isTrue();
		assertThat(annaOptional.get()).isEqualToComparingFieldByField(anna);
	}
	
	/**
	 * Test method for {@link com.yisebell.learningspringboot.dao.FakeDataDao#getUser(java.util.UUID)}.
	 */
	@Test
	public void testNotGetUser() {
		Optional<User> user = fakeDataDao.getUser(UUID.randomUUID());
		assertThat(user.isPresent()).isFalse();
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.dao.FakeDataDao#updateUser(com.yisebell.learningspringboot.model.User)}.
	 */
	@Test
	public void testUpdateUser() {
		UUID joeUserUid = fakeDataDao.getAllUsers().get(0).getUserUid();
		User newJoe = new User(joeUserUid,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		fakeDataDao.updateUser(newJoe);
		Optional<User> user = fakeDataDao.getUser(joeUserUid);
		assertThat(user.isPresent()).isTrue();
		
		assertThat(fakeDataDao.getAllUsers()).hasSize(1);
		assertThat(user.get()).isEqualToComparingFieldByField(newJoe);
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.dao.FakeDataDao#removeUser(java.util.UUID)}.
	 */
	@Test
	public void testRemoveUser() {
		UUID joeUserUid = fakeDataDao.getAllUsers().get(0).getUserUid();
		fakeDataDao.removeUser(joeUserUid);
		assertThat(fakeDataDao.getUser(joeUserUid).isPresent()).isFalse();
		assertThat(fakeDataDao.getAllUsers()).isEmpty();
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.dao.FakeDataDao#insertUser(java.util.UUID, com.yisebell.learningspringboot.model.User)}.
	 */
	@Test
	public void testInsertUser() {
		UUID userUid = UUID.randomUUID();
		User user = new User(userUid,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		fakeDataDao.insertUser(userUid, user);
		assertThat(fakeDataDao.getAllUsers()).hasSize(2);
		assertThat(fakeDataDao.getUser(userUid).get()).isEqualToComparingFieldByField(user);
	}

}
