package com.boulanger.mongotest.model;

import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@ToString
public class Customer {
	@Id
	public @NonNull String id;

	public @NonNull String firstName;
	public @NonNull String lastName;
	public @NonNull String eMail;

	public Customer(String firstName, String lastName, String eMail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
	}
}
