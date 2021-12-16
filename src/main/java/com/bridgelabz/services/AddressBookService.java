package com.bridgelabz.services;

import com.bridgelabz.builder.AddressBookBuilder;
import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.entity.AddressBookEntity;
import com.bridgelabz.exceptions.DataNotFoundException;
import com.bridgelabz.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RestController
public class AddressBookService {

    private static final String CONTACT_ADDED = "A contact added to AddressBook Successfully";
    private static final String CONTACT_UPDATED = "Contact updated in AddressBook successfully";
    private static final String ID_INVALID = "The Id Invalid";
    private static final String CONTACT_DELETED = "Contact in AddressBook Deleted successfully";

    @Autowired
    private AddressBookBuilder addressBookBuilder;
    @Autowired
    private AddressBookRepository addressBookRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<AddressBookDTO> getAddressBookList() {
        return addressBookRepository
                .findAll()
                .stream()
                .map(addressBookEntity ->
                        modelMapper.map(addressBookEntity, AddressBookDTO.class))
                .collect(Collectors.toList());
    }

    public String addAddress(AddressBookDTO addressBookDTO) {
        AddressBookEntity addressBookEntity = modelMapper.map(addressBookDTO, AddressBookEntity.class);
        addressBookRepository.save(addressBookEntity);
        return CONTACT_ADDED;
    }

    public String updateContactDetails(int id, AddressBookDTO addressBookDTO) {
        AddressBookEntity addressBookEntity = findContactById(id);
        AddressBookEntity addressBook = addressBookBuilder.buildEntity(addressBookDTO, addressBookEntity);
        addressBookRepository.save(addressBook);
        return CONTACT_UPDATED;
    }

    private AddressBookEntity findContactById(int id) {
        return addressBookRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ID_INVALID));
    }

    public String getDeleteContact(int id) {
        AddressBookEntity addressBookEntity = findContactById(id);
        addressBookRepository.delete(addressBookEntity);
        return CONTACT_DELETED;
    }


    public AddressBookDTO getAddressByID(int id) {
        AddressBookEntity addressBookEntity = findContactById(id);
        return modelMapper.map(addressBookEntity, AddressBookDTO.class);
    }
}
