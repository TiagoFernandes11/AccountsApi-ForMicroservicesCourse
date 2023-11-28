package com.udemy.Accounts.service.impl;

import com.udemy.Accounts.constants.AccountConstants;
import com.udemy.Accounts.dto.AccountsDTO;
import com.udemy.Accounts.dto.CustomerDTO;
import com.udemy.Accounts.entity.Account;
import com.udemy.Accounts.entity.Customer;
import com.udemy.Accounts.exception.CustomerAlreadyExistException;
import com.udemy.Accounts.exception.ResourceNotFoundException;
import com.udemy.Accounts.mapper.AccountsMapper;
import com.udemy.Accounts.mapper.CustomerMapper;
import com.udemy.Accounts.repository.AccountsRepository;
import com.udemy.Accounts.repository.CustomerRepository;
import com.udemy.Accounts.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements IAccountsService {

    @Autowired
    private AccountsRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer already registred with mobileNumber: "
                    + customerDTO.getMobileNumber());
        }
        customer.setCreatedDt(LocalDateTime.now());
        Account account = createNewAccount(customerRepository.save(customer));
        accountRepository.save(account);
    }

    private Account createNewAccount(Customer customer){
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        Integer randomAccNumber = 1000000000 + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedDt(LocalDateTime.now());
        return newAccount;
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Optional<Customer> customer = customerRepository.findByMobileNumber(mobileNumber);
        if(customer.isPresent()){
            CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer.get(), new CustomerDTO());
            Optional<Account> account = accountRepository.findByCustomerId(customer.get().getCustomerId());
            customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDto(account.get(), new AccountsDTO()));
            return customerDTO;
        }
        throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
    }
}
