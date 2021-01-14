package com.boulanger.mongotest.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
public class Customer {
	@Id
	public @NonNull String id;

	public @NonNull String firstName;
	public @NonNull String lastName;
	public @NonNull String eMail;

	public Customer(@NonNull String firstName, @NonNull String lastName, @NonNull String eMail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
	}
}
