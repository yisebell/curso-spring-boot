package com.yisebell.learningspringboot.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yisebell.learningspringboot.model.User;
import com.yisebell.learningspringboot.service.UserService;

@RestController
@RequestMapping(
	path = "/api/v1/users"
)
public class UserResource {

	private UserService userService;
	
	@Autowired
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(
		method = RequestMethod.GET
	)
	public List<User> fetchUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(
		method = RequestMethod.GET,
		path = "{userUid}"
	)
	public ResponseEntity<?> fetchUser(@PathVariable("userUid") UUID userUid) {
		Optional<User> userOptional = userService.getUser(userUid);
		if(userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorMessage("User: " + userUid + " was not found."));
	}
	
	@RequestMapping(
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
		int result = userService.insertUser(user);
		if(result == 1) {
			return ResponseEntity.ok().build();			
		}
		return ResponseEntity.badRequest().build();
	}
	
	class ErrorMessage {
		
		String errorMessage;

		public ErrorMessage(String errorMessage) {
			super();
			this.errorMessage = errorMessage;
		}

		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() {
			return errorMessage;
		}

		/**
		 * @param errorMessage the errorMessage to set
		 */
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		
	}
}
