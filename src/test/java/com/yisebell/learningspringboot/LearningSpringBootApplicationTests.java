package com.yisebell.learningspringboot;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.yisebell.learningspringboot.clientproxy.UserResourceV1;
import com.yisebell.learningspringboot.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class LearningSpringBootApplicationTests {

	@Autowired
	private UserResourceV1 userResourceV1;
	
	@Test(expected = NotFoundException.class)
	public void itShouldFetchAllUsers() {
		List<User> users = userResourceV1.fetchUsers(null);
		assertThat(users).hasSize(1);
	}

}
