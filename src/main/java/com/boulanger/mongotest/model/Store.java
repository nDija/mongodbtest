package com.boulanger.mongotest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString

public class Store {
    private String name;
    private String number;
    private String address;
    @Id
    private String id;

    public Store(String name, String number, String address){
        this.name = name;
        this.number = number;
        this.address = address;

    }

}
