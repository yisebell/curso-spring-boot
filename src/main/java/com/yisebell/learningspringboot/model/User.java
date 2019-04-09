package com.yisebell.learningspringboot.model;

import java.util.UUID;

public class User {
	
	private final UUID userUid;
	private final String firstName;
	private final String lastName;
	private final Gender gender;
	private final Integer age;
	private final String email;
	
	public User(UUID userUid, String firstName, String lastName, 
			Gender gender, Integer age, String email) {
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



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userUid=" + userUid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + ", email=" + email + "]";
	}
	
}
