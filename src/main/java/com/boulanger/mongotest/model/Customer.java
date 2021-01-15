package com.boulanger.mongotest.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Customer {

    @Id
    public @NonNull String id;
    public @NonNull String firstName;
    public @NonNull String lastName;

    @Indexed(name = "idx_email", unique = true)
    public @NonNull String eMail;

    public Customer(@NonNull String firstName, @NonNull String lastName, @NonNull String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }
}
