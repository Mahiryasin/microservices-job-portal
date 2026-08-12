package com.project.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.DTO.AccountDTO;
import com.project.DTO.CustomerDTO;
import com.project.DTO.ResponseDTO;

public interface IAccountService {

    public AccountDTO createAccount(CustomerDTO customerDTO);

    public List<AccountDTO> fetchAccountDetails (String mobileNumber);

    public boolean updateAccount( AccountDTO accountDTO);

     public Boolean deleteAccount( String mobileNumber);



}
