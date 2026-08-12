package com.project.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.DTO.AccountDTO;
import com.project.Entity.Account;
import com.project.Entity.Customer;

@Mapper(componentModel = "spring")
public  interface AccountMapper {

     AccountDTO mapToAccountDTO(Account account);

     @Mapping(target = "customer", ignore = true)
     @Mapping(target = "created_at", ignore = true)
     @Mapping(target = "created_by", ignore = true)
     @Mapping(target = "updated_at", ignore = true)
     @Mapping(target = "updated_by", ignore = true)
     Account mapToAccount(AccountDTO accountDTO);

     List<AccountDTO> mapToAccountDTO(List<Account> accounts);

     @Mapping(target = "customer", ignore = true)
     @Mapping(target = "created_at", ignore = true)
     @Mapping(target = "created_by", ignore = true)
     @Mapping(target = "updated_at", ignore = true)
     @Mapping(target = "updated_by", ignore = true)
      Account mapToAccount(Account account);     

}
