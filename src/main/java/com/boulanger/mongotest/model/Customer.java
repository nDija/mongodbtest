package com.boulanger.mongotest.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Getter
@ToString
public class Customer {


	@Id
	public @NonNull String id;
	public @NonNull String firstName;
	public @NonNull String lastName;

}
