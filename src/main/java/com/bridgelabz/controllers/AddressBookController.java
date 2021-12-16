package com.bridgelabz.controllers;

import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.services.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address-book-service")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @GetMapping(value = "/contact-list")
    public List<AddressBookDTO> getAllAddressBookList() {
        return addressBookService.getAddressBookList();
    }

    @PostMapping(value = "/address")
    public ResponseEntity<String> addContact(
            @RequestBody AddressBookDTO addressBookDTO
    ) {
        return new ResponseEntity<>(addressBookService.addAddress(addressBookDTO), HttpStatus.OK);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<String> updateContact(
            @PathVariable(value = "id") int id,
            @RequestBody AddressBookDTO addressBookDTO
    ) {
        return new ResponseEntity<>(addressBookService.updateContactDetails(id, addressBookDTO),
                HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String> deleteContact(
            @PathVariable(value = "id") int id
    ) {
        return new ResponseEntity<>(addressBookService.getDeleteContact(id), HttpStatus.OK);
    }

    @GetMapping(value = "/address/{id}")
    public ResponseEntity<String> getContactByID(
            @PathVariable(value = "id") int id
    ) {
        AddressBookDTO addressBookDTO = addressBookService.getAddressByID(id);
        return new ResponseEntity(addressBookDTO, HttpStatus.OK);
    }
}
