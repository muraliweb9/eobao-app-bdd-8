package com.murali.natwest.eobao8.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountTypes {

    List<CustomerAccountType> customerAccountTypes;
}