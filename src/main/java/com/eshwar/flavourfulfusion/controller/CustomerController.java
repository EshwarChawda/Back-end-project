package com.eshwar.flavourfulfusion.controller;

import com.eshwar.flavourfulfusion.dto.CustomerRequest;
import com.eshwar.flavourfulfusion.dto.CustomerResponse;
import com.eshwar.flavourfulfusion.dto.LoginRequest;
import com.eshwar.flavourfulfusion.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}
