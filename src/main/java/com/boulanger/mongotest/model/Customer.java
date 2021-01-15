package com.boulanger.mongotest.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@ToString
@RequiredArgsConstructor
public class Customer {

    @Id
    public String id;
    public @NonNull String firstName;
    public @NonNull String lastName;

    @Indexed(name = "idx_email", unique = true)
    public @NonNull String eMail;

}
