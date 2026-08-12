package com.project.Service;

import com.project.Mapper.AccountMapperImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.Constant.ACCOUNT_CONSTANTS;
import com.project.DTO.AccountDTO;
import com.project.DTO.CustomerDTO;
import com.project.Entity.Account;
import com.project.Entity.Customer;
import com.project.Exception.ALREADY_EXIST_MOBILE_NUMBER;
import com.project.Exception.RESOURCE_NOT_FOUND_EXCEPTION;
import com.project.Mapper.AccountMapper;
import com.project.Mapper.CustomerMapper;
import com.project.Reporistory.AccountRepository;
import com.project.Reporistory.CustomerReporistory;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data

public class AccountService  implements IAccountService{

   



    private final AccountMapper _accountMapper;

    private final CustomerMapper _customerMapper;

    private final CustomerReporistory _customerReporistory;

    private final AccountRepository _accountRepository;






    
    @Override
    
    @org.springframework.transaction.annotation.Transactional
    public AccountDTO createAccount(CustomerDTO customerDTO) {
       

       Optional<Customer>opt_customer=_customerReporistory.findByMobileNumber(customerDTO.getMobileNumber());

       if(opt_customer.isPresent()){
           throw new ALREADY_EXIST_MOBILE_NUMBER("Mobile number already exists");
       }

      Customer customer= _customerMapper.mapToCustomer(customerDTO);

      

      

     Customer save_Customer=_customerReporistory.save(customer);

      Account save_Account=_accountRepository.save(createAccount(save_Customer));

      AccountDTO accountDTO =_accountMapper.mapToAccountDTO(save_Account);

      return accountDTO;

 }

    private Account createAccount(Customer customer){
        Account account=new Account();

        Long account_Number=10000L + new Random().nextLong(100000);
        account.setCustomer(customer);
        account.setAccount_type(ACCOUNT_CONSTANTS.SAVINGS);
        account.setBranch_address(ACCOUNT_CONSTANTS.ADDRESS);
        account.setAccount_number(account_Number);
        return account;
        
    }

    @Override
    public List<AccountDTO> fetchAccountDetails(String mobileNumber) {
          Optional<Customer>optCustomer=Optional.ofNullable(
          _customerReporistory.findByMobileNumber(mobileNumber).orElseThrow(()->new RESOURCE_NOT_FOUND_EXCEPTION("Customer","MobileNumber",mobileNumber) ));
          List<Account> accounts=_accountRepository.findByCustomer(optCustomer.get()).orElseThrow(()->new RESOURCE_NOT_FOUND_EXCEPTION("Account","customer_Id",optCustomer.get().getCustomer_id().toString()));
           CustomerDTO customerDTO=_customerMapper.mapToCustomerDTO(optCustomer.get());
           List<AccountDTO> accountDTO=_accountMapper.mapToAccountDTO(accounts);
           accountDTO.stream().forEach((account)->account.setCustomerDTO(customerDTO));
         

           return accountDTO;
         }

    @Override
    public boolean updateAccount(AccountDTO accountDTO) {
     boolean isUpdated=false;
      Account account = _accountMapper.mapToAccount(accountDTO);
      Customer customer_ = _customerMapper.mapToCustomer(accountDTO.getCustomerDTO());
      Customer customer =_customerReporistory.findByMobileNumber(customer_.getMobileNumber()).orElseThrow(()->new RESOURCE_NOT_FOUND_EXCEPTION("Customer","MobileNumber",customer_.getMobileNumber()));
    
     Optional<Account> _optAccount=Optional.ofNullable(_accountRepository.findById(account.getAccount_number()).orElseThrow(()->new RESOURCE_NOT_FOUND_EXCEPTION("Account","AccountNumber",account.getAccount_number().toString())));
 
         BeanUtils.copyProperties(account,_optAccount.get(),"customer","created_at","created_by","updated_at","updated_by");
         isUpdated=true;
        BeanUtils.copyProperties(customer_, customer, "customer_id", "created_at", "created_by", "updated_at", "updated_by");

        Customer save_Customer=_customerReporistory.save(customer);
        _optAccount.get().setCustomer(save_Customer);
        _accountRepository.save(_optAccount.get());

        return isUpdated;
      
      }

    @Override
    public Boolean deleteAccount(String mobileNumber ) {

            boolean isDeleted=false;
            Customer customer= _customerReporistory.findByMobileNumber(mobileNumber).orElseThrow(()->new RESOURCE_NOT_FOUND_EXCEPTION("Customer","MobileNumber",mobileNumber));
        List<Account>accounts = _accountRepository.findByCustomer(customer).orElseThrow(()->new RESOURCE_NOT_FOUND_EXCEPTION("Account","CustomerId",customer.getCustomer_id().toString()));
         _accountRepository.deleteAll(accounts);
         
         _customerReporistory.deleteByMobileNumber(mobileNumber);
         isDeleted=true;
         return isDeleted;
    } 
        

}
