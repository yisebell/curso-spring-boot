package com.yisebell.learningspringboot.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private final UUID userUid;
	private final String firstName;
	private final String lastName;
	private final Gender gender;
	private final Integer age;
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
