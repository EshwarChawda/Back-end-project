package com.eshwar.flavourfulfusion.mapper;

import com.eshwar.flavourfulfusion.dto.CustomerRequest;
import com.eshwar.flavourfulfusion.dto.CustomerResponse;
import com.eshwar.flavourfulfusion.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address()) // Add address
                .city(request.city())       // Add city
                .pincode(request.pincode()) // Add pincode
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}
