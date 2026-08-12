package com.project.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.DTO.CustomerDTO;
import com.project.Entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    
    CustomerDTO mapToCustomerDTO(Customer customer);

    @Mapping(target = "customer_id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "created_by", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "updated_by", ignore = true)
    Customer mapToCustomer(CustomerDTO customerDTO);

}
