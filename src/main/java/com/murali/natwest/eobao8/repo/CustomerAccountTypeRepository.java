package com.murali.natwest.eobao8.repo;


import com.murali.natwest.eobao8.data.CustomerAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountTypeRepository extends JpaRepository<CustomerAccountType, String> {
}