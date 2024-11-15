package com.eshwar.flavourfulfusion.service;

import com.eshwar.flavourfulfusion.dto.CustomerRequest;
import com.eshwar.flavourfulfusion.dto.CustomerResponse;
import com.eshwar.flavourfulfusion.dto.LoginRequest;
import com.eshwar.flavourfulfusion.entity.Customer;
import com.eshwar.flavourfulfusion.exception.CustomerNotFoundException;
import com.eshwar.flavourfulfusion.helper.EncryptionService;
import com.eshwar.flavourfulfusion.helper.JWTHelper;
import com.eshwar.flavourfulfusion.mapper.CustomerMapper;
import com.eshwar.flavourfulfusion.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);

        // Save encoded Password


        customer.setPassword(encryptionService.encode(customer.getPassword()));

        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }
}
