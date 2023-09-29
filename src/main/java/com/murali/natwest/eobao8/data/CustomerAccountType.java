package com.murali.natwest.eobao8.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customeraccounttypes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccountType {

    @Id
    private String id;
    private CustomerType customerType;

    private AccountType accountType;


}
