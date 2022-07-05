package com.learning.controller;

import com.learning.entity.*;
import com.learning.exception.InsufficentFundsException;
import com.learning.pojo.AccountRequest;
import com.learning.pojo.BeneficaryResquest;
import com.learning.pojo.CustomerRequest;
import com.learning.pojo.ErrorMapper;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;
import com.learning.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody User user) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/account/{accountNo}")
    public ResponseEntity getAccountDetails(@PathVariable("accountNo") int accNo) {
        Account account;
        try {
            account = accountService.getAccountById(accNo);
        } catch (Exception nse) {
            account = null;
        }
        return account == null ? new ResponseEntity(new ErrorMapper("Account not found"), HttpStatus.OK) : new ResponseEntity(account, HttpStatus.OK);

    }

    //Start here
    @GetMapping("/beneficiary")
    public ResponseEntity<List<Beneficary>> getApprovedBeneficiary() {

        return new ResponseEntity<List<Beneficary>>(beneficiaryService.findByIsApproved("No"), HttpStatus.OK);

    }

    @PutMapping("/beneficiary")
    public ResponseEntity approveBeneficiary(@RequestBody BeneficaryResquest beneficaryRequest) {
        Beneficary beneficary;
        System.out.println(beneficaryRequest);
        try {
            beneficary = beneficiaryService.findByAccountNumber(beneficaryRequest.getBeneficiaryAcNo());
            beneficary.setApproved(beneficaryRequest.getApproved());
            beneficary.setDate(beneficaryRequest.getBeneficiaryAddedDate());

            beneficary = beneficiaryService.updateBeneficary(beneficary);
        }catch (Exception ex){
            beneficary = null;
        }
        return beneficary == null ? new ResponseEntity(new ErrorMapper("Sorry beneficiary not approved"), HttpStatus.OK) : new ResponseEntity(beneficary, HttpStatus.OK);

    }

    @GetMapping("/accounts/approve")
    public ResponseEntity<List<Account>> getApprovedAccounts() {

        return new ResponseEntity<List<Account>>(accountService.findByIsApproved(false), HttpStatus.OK);

    }

    @PutMapping("/accounts/approve")
    public ResponseEntity approveAccounts(@RequestBody AccountRequest account) {
        Account account1;
        try {
            account1 = accountService.getAccountById(account.getAccountNumber());

            account1.setApproved(true);
        }catch (Exception ex){
            account1 = null;
        }
        return account1==null?  new ResponseEntity(new ErrorMapper("Approving of account was not successful"), HttpStatus.OK) : new ResponseEntity(accountService.updateAccount(account1), HttpStatus.OK);

    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer() {

        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @PutMapping("/customer")
    public ResponseEntity enableCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer;
        System.out.println(customerRequest);
        try {
            customer = customerService.getCustomerById(customerRequest.getCustomerId());
            customer.setStatus(customerRequest.getStatus());
        }catch (Exception ex){
            customer = null;
        }

        System.out.println(customer);
        return customer == null? new ResponseEntity(new ErrorMapper("Customer status not changed"), HttpStatus.OK) :new ResponseEntity(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity getCustomerId(@PathVariable("customerId") int customerId) {
        Customer customer;
        System.out.println(customerService.getAllCustomer());

        try {
            customer = customerService.getCustomerById(customerId);
        }catch (Exception ex){
            customer = null;
        }
        System.out.println(customer);

        return customer == null? new ResponseEntity(new ErrorMapper("Customer Not Found"), HttpStatus.NOT_FOUND) :new ResponseEntity(customer, HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity transfer(@RequestBody Transaction transaction) {
        Account fromAccount, toAccount ;
        //log
        System.out.println(transaction);
        Transaction transaction1;

        try {
            fromAccount = accountService.getAccountById(transaction.getFromAcc());
            toAccount = accountService.getAccountById(transaction.getToAcc());
            if((fromAccount.getAccountBalance() < transaction.getAmount())||!fromAccount.isApproved()||!toAccount.isApproved() )
                throw new RuntimeException();
            fromAccount.setAccountBalance(fromAccount.getAccountBalance() - transaction.getAmount());
            toAccount.setAccountBalance(fromAccount.getAccountBalance() + transaction.getAmount());

            accountService.updateAccount(fromAccount);
            accountService.updateAccount(toAccount);
            transaction.setDate(new Date());

            transaction1 = transactionService.addTransaction(transaction);

        }catch (Exception ex){
            fromAccount=null;
            toAccount=null;
            transaction1 =null;
        }

        return transaction1==null? new ResponseEntity(new ErrorMapper("From/To Account Number Not Valid"), HttpStatus.NOT_FOUND)  : new ResponseEntity(transaction1, HttpStatus.OK);
    }


}
