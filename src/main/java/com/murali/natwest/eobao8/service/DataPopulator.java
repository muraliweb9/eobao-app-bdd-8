package com.murali.natwest.eobao8.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murali.natwest.eobao8.data.CustomerAccountTypes;
import com.murali.natwest.eobao8.repo.CustomerAccountTypeRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service
@Slf4j
public class DataPopulator {

    @Autowired
    private CustomerAccountTypeRepository customerAccountTypeRepository;

    @PostConstruct
    @SneakyThrows
    public void init() {

        InputStream in = null;
        ObjectMapper mapper = new ObjectMapper();

        log.info("Pre-population customer account type data ...");
        in = this.getClass().getClassLoader().getResourceAsStream("customer-account-type.json");
        CustomerAccountTypes customerAccountTypes = mapper.readValue(in, CustomerAccountTypes.class);
        log.info("CustomerAccountTypes={} loaded", customerAccountTypes.getCustomerAccountTypes().size());
        customerAccountTypes.getCustomerAccountTypes().stream().forEach(c -> customerAccountTypeRepository.save(c));

    }

}
