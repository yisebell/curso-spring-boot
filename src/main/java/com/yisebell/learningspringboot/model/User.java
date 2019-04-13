package com.yisebell.learningspringboot.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private final UUID userUid;
	@NotNull(message = "first name required")
	private final String firstName;
	@NotNull
	private final String lastName;
	@NotNull
	private final Gender gender;
	@NotNull
	@Max(value = 112)
	@Min(value = 0)
	private final Integer age;
	@NotNull
	@Email
	private final String email;
	
	public User(@JsonProperty("userUid") UUID userUid,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName, 
			@JsonProperty("gender") Gender gender,
			@JsonProperty("age") Integer age,
			@JsonProperty("email") String email) {
		this.userUid = userUid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}

	public enum Gender{
		MALE,
		FEMALE
	}


	/**
	 * @return the userUid
	 */
	@JsonProperty("id")
	public UUID getUserUid() {
		return userUid;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}



	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	public String getFullname() {
		return firstName + " " + lastName;
	}
	
	public int getDateOfBirth() {
		return LocalDate.now().minusYears(age).getYear();
	}

	public static User newUser(UUID userUid, User user) {
		return new User(userUid,user.getFirstName(),user.getLastName(),user.getGender(),user.getAge(),user.getEmail());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userUid=" + userUid + 
				", firstName=" + firstName + 
				", lastName=" + lastName + 
				", gender=" + gender
				+ ", age=" + age + 
				", email=" + email + "]";
	}
	
}
