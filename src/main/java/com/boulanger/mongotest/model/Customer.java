package com.boulanger.mongotest.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Customer {
   @Id
   private String id;
   @NonNull
   private String firstName;
   @NonNull
   private String lastName;
   @NonNull
   @Indexed(unique=true)
   private String eMail;


   public Customer(@NonNull String firstName, @NonNull String lastName, @NonNull String eMail){
      this.firstName = firstName;
      this.lastName = lastName;
      this.eMail = eMail;
   }
}
