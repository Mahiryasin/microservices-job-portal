package com.project.Reporistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.Account;
import com.project.Entity.Customer;

public interface AccountRepository extends JpaRepository<Account,Long>{
   
    Optional<List<Account>>findByCustomer(Customer customer);
}
