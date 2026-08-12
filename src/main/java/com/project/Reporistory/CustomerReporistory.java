package com.project.Reporistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.project.Entity.Customer;

import jakarta.transaction.Transactional;



public interface CustomerReporistory extends JpaRepository<Customer,Long>{
 
    Optional<Customer> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);
}
