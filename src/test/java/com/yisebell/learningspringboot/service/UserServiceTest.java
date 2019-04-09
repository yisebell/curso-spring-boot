/**
 * 
 */
package com.yisebell.learningspringboot.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.yisebell.learningspringboot.dao.FakeDataDao;
import com.yisebell.learningspringboot.model.User;
import com.yisebell.learningspringboot.model.User.Gender;

/**
 * @author ljhidalgo
 *
 */
public class UserServiceTest {
	
	@Mock
	private FakeDataDao fakeDataDao;
	private UserService userService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(fakeDataDao);
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.service.UserService#getAllUsers()}.
	 */
	@Test
	public void testGetAllUsers() {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		ImmutableList<User> users = new ImmutableList.Builder<User>()
			.add(anna)
			.build();
		given(fakeDataDao.getAllUsers()).willReturn(users);
		List<User> allUsers = userService.getAllUsers();
		assertThat(allUsers).hasSize(1);
		
		User user = allUsers.get(0);
		
		assertUserFields(user);
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.service.UserService#getUser(java.util.UUID)}.
	 */
	@Test
	public void testGetUser() {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		given(fakeDataDao.getUser(annaUid)).willReturn(Optional.of(anna));
		
		Optional<User> userOptional = userService.getUser(annaUid);
		
		assertThat(userOptional.isPresent()).isTrue();
		User user = userOptional.get(); 
		
		assertUserFields(user);
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.service.UserService#updateUser(com.yisebell.learningspringboot.model.User)}.
	 */
	@Test
	public void testUpdateUser() {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		given(fakeDataDao.getUser(annaUid)).willReturn(Optional.of(anna));
		given(fakeDataDao.updateUser(anna)).willReturn(1);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		int updateResult = userService.updateUser(anna);
		
		verify(fakeDataDao).getUser(annaUid);
		verify(fakeDataDao).updateUser(captor.capture());
		
		User user = captor.getValue();
		
		assertUserFields(user);
		assertThat(updateResult).isEqualTo(1);
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.service.UserService#removeUser(java.util.UUID)}.
	 */
	@Test
	public void testRemoveUser() {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		given(fakeDataDao.getUser(annaUid)).willReturn(Optional.of(anna));
		given(fakeDataDao.removeUser(annaUid)).willReturn(1);
		
		int deleteResult = userService.removeUser(annaUid);
		
		verify(fakeDataDao).getUser(annaUid);
		verify(fakeDataDao).removeUser(annaUid);
		
		assertThat(deleteResult).isEqualTo(1);
	}

	/**
	 * Test method for {@link com.yisebell.learningspringboot.service.UserService#insertUser(com.yisebell.learningspringboot.model.User)}.
	 */
	@Test
	public void testInsertUser() {
		User anna = new User(null,"anna","montana",
				Gender.FEMALE,30,"anna@mail.com");
		given(fakeDataDao.insertUser(any(UUID.class), eq(anna))).willReturn(1);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		int insertResult = userService.insertUser(anna);
		
		verify(fakeDataDao).insertUser(any(UUID.class), captor.capture());
		
		User user = captor.getValue();
		
		assertUserFields(user);
		assertThat(insertResult).isEqualTo(1);
	}
	
	private void assertUserFields(User user) {
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("anna");
		assertThat(user.getLastName()).isEqualTo("montana");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getEmail()).isEqualTo("anna@mail.com");
		assertThat(user.getUserUid()).isInstanceOf(UUID.class);
	}
}
