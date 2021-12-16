package com.bridgelabz.builder;

import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.entity.AddressBookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressBookBuilder {
    private ModelMapper modelMapper = new ModelMapper();

    public AddressBookEntity buildEntity(AddressBookDTO addressBookDTO,
                                         AddressBookEntity addressBookEntity) {
        modelMapper.map(addressBookDTO, addressBookEntity);
        return addressBookEntity;
    }
}
