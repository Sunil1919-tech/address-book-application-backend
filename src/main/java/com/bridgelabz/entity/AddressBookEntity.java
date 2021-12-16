package com.bridgelabz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addressbook_service")
public class AddressBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNum;
    private String emailID;
}
