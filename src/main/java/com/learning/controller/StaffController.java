package com.learning.controller;

import com.learning.entity.Account;
import com.learning.entity.Beneficary;
import com.learning.entity.Customer;
import com.learning.entity.Transaction;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;
import com.learning.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Customer> authenticate(@RequestBody Account account) {
        return new ResponseEntity<Customer>(HttpStatus.OK);
    }

    @GetMapping("/account/{accountNo}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable("accountNo") int accNo) {
        return new ResponseEntity<Account>(accountService.getAccountById(accNo),HttpStatus.OK);

    }
//Start here
    @GetMapping("/beneficiary")
    public ResponseEntity<List<Beneficary>> getApprovedBeneficiary() {

        return new ResponseEntity<List<Beneficary>>(beneficiaryService.findByIsApproved("Yes"),HttpStatus.OK);

    }

    @PutMapping("/beneficiary")
    public ResponseEntity<Beneficary> updateBeneficiary(@RequestBody Beneficary beneficary) {

        return new ResponseEntity<Beneficary>( beneficiaryService.updateBeneficary(beneficary) ,HttpStatus.OK);

    }

    @GetMapping("/accounts/approve")
    public ResponseEntity<List<Account>> getApprovedAccounts() {

        return new ResponseEntity<List<Account>>(accountService.findByIsApproved(true),HttpStatus.OK);

    }

    @PutMapping("/accounts/approve")
    public ResponseEntity<Account> approveAccounts(@RequestBody Account account) {
        accountService.updateAccount(account);

        return new ResponseEntity<Account>(HttpStatus.OK);

    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer() {

        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(),HttpStatus.OK);
    }

    @PutMapping("/customer")
    public ResponseEntity<Customer> enableCustomer(@RequestBody Customer customer) {

        return new ResponseEntity<Customer>(customerService.updateCustomer(customer),HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable("customerID") int customerId) {

        return new ResponseEntity<Customer>(customerService.getCustomerById(customerId),HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody Transaction transaction) {

        Account fromAccount = accountService.getAccountById(transaction.getFromAcc());
        Account toAccount = accountService.getAccountById(transaction.getToAcc());
        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - transaction.getAmount());
        toAccount.setAccountBalance(fromAccount.getAccountBalance() + transaction.getAmount());

        accountService.updateAccount(fromAccount);
        accountService.updateAccount(toAccount);

        return new ResponseEntity<Transaction>(transactionService.addTransaction(transaction),HttpStatus.OK);
    }



}
