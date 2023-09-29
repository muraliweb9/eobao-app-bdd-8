package com.murali.natwest.eobao8.service;

import com.murali.natwest.eobao8.data.AccountTypes;
import com.murali.natwest.eobao8.data.CustomerType;
import com.murali.natwest.eobao8.repo.CustomerAccountTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("app-open")
@Slf4j
public class ApplicationOpenService {

    @Autowired
    private CustomerAccountTypeRepository customerAccountTypeRepository;

    @GetMapping("/available")
    public AccountTypes getAvailable() {
        log.info("Getting all the available type of accounts");
        return AccountTypes.builder().accountTypes(
                customerAccountTypeRepository
                        .findAll()
                        .stream()
                        .map(c -> c.getAccountType())
                        .collect(Collectors.toList())
        ).build();

    }

    @GetMapping("/available-for/{customerType}")
    public AccountTypes getAvailableFor(@PathVariable String customerType) {
        CustomerType customerTypeEnum = CustomerType.valueOf(customerType);
        log.info("Getting available type of accounts for customer type {}", customerType);
        return AccountTypes.builder().accountTypes(
                customerAccountTypeRepository
                        .findAll()
                        .stream()
                        .filter(c -> c.getCustomerType().equals(customerTypeEnum))
                        .map(c -> c.getAccountType())
                        .collect(Collectors.toList())
        ).build();
    }

}
