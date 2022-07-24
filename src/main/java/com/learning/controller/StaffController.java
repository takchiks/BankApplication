package com.learning.controller;

import com.learning.entity.Account;
import com.learning.entity.Beneficary;
import com.learning.entity.Customer;
import com.learning.entity.Staff;
import com.learning.entity.Transaction;
import com.learning.entity.User;
import com.learning.enums.PaymentType;
import com.learning.enums.RoleType;
import com.learning.others.UsernamePassword;
import com.learning.pojo.AccountRequest;
import com.learning.pojo.BeneficaryResquest;
import com.learning.pojo.CustomerRequest;
import com.learning.pojo.ErrorMapper;
import com.learning.pojo.TokenPojo;
import com.learning.repo.UserRepo;
import com.learning.securtiy.JWTUtil;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;
import com.learning.service.StaffService;
import com.learning.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("/api/staff")
public class StaffController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BeneficiaryService beneficiaryService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> authenticate(@RequestBody UsernamePassword body) {

		try {
			System.out.println(body);
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getUsername(), body.getPassword());

			authManager.authenticate(authInputToken);

			RoleType role = userRepo.findByUserName(body.getUsername()).get().getRole();

			if (role != RoleType.STAFF) {
				return new ResponseEntity(new ErrorMapper("NOT STAFF!! CHECK USER"), HttpStatus.BAD_REQUEST);
			}

			String token = jwtUtil.generateToken(body.getUsername());

			return new ResponseEntity<>(Collections.singletonMap("jwt", token), HttpStatus.ACCEPTED);
		} catch (AuthenticationException authExc) {
			return new ResponseEntity(new ErrorMapper("WRONG USERNAME OR PASSWORD"), HttpStatus.BAD_REQUEST);
		}
//        return new ResponseEntity(HttpStatus.OK);/
	}

	@PreAuthorize("hasAuthority('STAFF')")
	@GetMapping("/account/{accountNo}")
	public ResponseEntity getAccountDetails(@PathVariable("accountNo") int accNo) {
		Account account;
		try {
			System.out.println(accNo);
			account = accountService.getAccountById(accNo);
			System.out.println(account);
		} catch (Exception nse) {
			account = null;
			nse.printStackTrace();
		}
		return account == null ? new ResponseEntity(new ErrorMapper("Account not found"), HttpStatus.OK)
				: new ResponseEntity(account, HttpStatus.OK);

	}

	// Start here
	// @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
	@PreAuthorize("hasAuthority('STAFF')")
	@GetMapping("/beneficiary")
	public ResponseEntity<List<Beneficary>> getApprovedBeneficiary() {
		System.out.println("approved beneficary");

		return new ResponseEntity<List<Beneficary>>(beneficiaryService.findByIsApproved("No"), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('STAFF')")
	@PutMapping("/beneficiary")
	public ResponseEntity approveBeneficiary(@RequestBody BeneficaryResquest beneficaryRequest) {
		Beneficary beneficary = null;
		List<Beneficary> beneficaryList;
		System.out.println(beneficaryRequest);
		try {
		
			beneficaryList = beneficiaryService.getAllBeneficiary();
			for (Beneficary beneficary1 : beneficaryList) {
				if (beneficary1.getAccountNumber() == beneficaryRequest.getBeneficiaryAcNo()) {
					beneficary = beneficary1;
			    System.out.println("The account number is "+beneficary1.getAccountNumber());		
				}

			}
			System.out.println("approving beneficary");
//            beneficary = beneficiaryService.findByAccountNumber(beneficaryRequest.getBeneficiaryAcNo());
			beneficary.setApproved(beneficaryRequest.getApproved());
			beneficary.setDate(beneficaryRequest.getBeneficiaryAddedDate());

			beneficary = beneficiaryService.updateBeneficary(beneficary);
		} catch (Exception ex) {
			ex.printStackTrace();
			beneficary = null;
		}
		return beneficary == null ? new ResponseEntity(new ErrorMapper("Sorry beneficiary not approved"), HttpStatus.OK)
				: new ResponseEntity(beneficary, HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('STAFF')")
	@GetMapping("/accounts/approve")
	public ResponseEntity<List<Account>> getApprovedAccounts() {

		return new ResponseEntity<List<Account>>(accountService.findByIsApproved(true), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('STAFF')")
	@PutMapping("/accounts/approve")
	public ResponseEntity approveAccounts(@RequestBody AccountRequest account) {
		Account account1;
		try {
			account1 = accountService.getAccountById(account.getAccountNumber());

			account1.setApproved(true);
			accountService.updateAccount(account1);
		} catch (Exception ex) {
			account1 = null;
			ex.printStackTrace();
		}
		return account1 == null
				? new ResponseEntity(new ErrorMapper("Approving of account was not successful"), HttpStatus.OK)
				: new ResponseEntity(accountService.updateAccount(account1), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('STAFF')")
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomer() {

		return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}/transaction1")
	public ResponseEntity<List<Transaction>> getTransaction(
			@PathVariable(name = "customerId") int customerId) {
		List<Transaction> transaction = transactionService.getAllTransaction();
		List<Transaction> finalTransaction = new ArrayList<Transaction>();
		try {
			Customer customer = customerService.getCustomerById(customerId);
			List<Account> account = customer.getAccount();
			for(Account acc:account) {
				for(Transaction trans:transaction) {
					if(acc.getAccountNumber()==trans.getFromAcc() || acc.getAccountNumber()==trans.getToAcc()) {
						finalTransaction.add(trans);
					}
				}
			
			}
			return new ResponseEntity<List<Transaction>>(finalTransaction, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Transaction>>(new ArrayList<Transaction>(), HttpStatus.OK);
		}
	}

	@GetMapping("/{accountNumber}/transaction2")
	public ResponseEntity<List<Transaction>> getTransactionByAccount(
			@PathVariable(name = "accountNumber") int accountNumber) {
		List<Transaction> transaction = transactionService.getAllTransaction();
		List<Transaction> finalTransaction = new ArrayList<Transaction>();
		try {
			//Customer customer = customerService.getCustomerById(accountNumber);
			Account account = accountService.getAccountById(accountNumber);
			// List<Account> account = customer.getAccount();
			// for(Account acc:account) {
				for(Transaction trans:transaction) {
					if(account.getAccountNumber()==trans.getFromAcc()|| account.getAccountNumber()==trans.getToAcc()) {
						finalTransaction.add(trans);
					}
				}
			
			//}
			return new ResponseEntity<List<Transaction>>(finalTransaction, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Transaction>>(new ArrayList<Transaction>(), HttpStatus.OK);
		}
	}

	
	@PutMapping("/customer")
	public ResponseEntity enableCustomer(@RequestBody CustomerRequest customerRequest) {
		Customer customer;
		System.out.println(customerRequest);
		try {
			customer = customerService.getCustomerById(customerRequest.getCustomerId());
			customer.setStatus(customerRequest.getStatus());
			customerService.updateCustomer(customer);
			System.out.println("enable customer");
		} catch (Exception ex) {
			customer = null;
		}

		System.out.println(customer);
		return customer == null ? new ResponseEntity(new ErrorMapper("Customer status not changed"), HttpStatus.OK)
				: new ResponseEntity(customerService.updateCustomer(customer), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('STAFF')")
	@GetMapping("/customer/{customerId}")
	public ResponseEntity getCustomerId(@PathVariable("customerId") int customerId) {
		Customer customer;
		System.out.println(customerService.getAllCustomer());

		try {
			customer = customerService.getCustomerById(customerId);
		} catch (Exception ex) {
			customer = null;
		}
		System.out.println(customer);

		return customer == null ? new ResponseEntity(new ErrorMapper("Customer Not Found"), HttpStatus.NOT_FOUND)
				: new ResponseEntity(customer, HttpStatus.OK);
	}

	// @PreAuthorize("hasAuthority('STAFF')")
	@PutMapping("/transfer")
	public ResponseEntity transfer(@RequestBody Transaction transaction) {
		Account fromAccount, toAccount;
		// log
		System.out.println(transaction);
		Transaction transaction1;

		try {
			transaction.setDate(new Date());
			fromAccount = accountService.getAccountById(transaction.getFromAcc());
			toAccount = accountService.getAccountById(transaction.getToAcc());
			if ((fromAccount.getAccountBalance() < transaction.getAmount()) || !fromAccount.isApproved()
					|| !toAccount.isApproved())
				throw new RuntimeException();
			fromAccount.setAccountBalance(fromAccount.getAccountBalance() - transaction.getAmount());
			toAccount.setAccountBalance(fromAccount.getAccountBalance() + transaction.getAmount());
 
			List list = new ArrayList();
			list.addAll(toAccount.getTransaction());
			list.add(transaction);	
			toAccount.setTransaction(list);
		    
			List list2 = new ArrayList();
			list2.addAll(fromAccount.getTransaction());
			list2.add(transaction);
			toAccount.setTransaction(list2);
			
			// (String reference, double amount, int toAcc, int fromAcc, String reason,PaymentType paymentType)
		  Transaction t1 = new Transaction(transaction.getReference(),transaction.getAmount(), transaction.getToAcc(), transaction.getFromAcc() , transaction.getReason(), transaction.getPaymentType());
		//  t1 = transaction
		
			
			accountService.updateAccount(fromAccount);
			accountService.updateAccount(toAccount);
			

			// transaction1 = transactionService.addTransaction(transaction);

		} catch (Exception ex) {
			fromAccount = null;
			toAccount = null;
			transaction= null;
			
			ex.printStackTrace();
		}

		return transaction == null
				? new ResponseEntity(new ErrorMapper("From/To Account Number Not Valid"), HttpStatus.NOT_FOUND)
				: new ResponseEntity(transaction, HttpStatus.OK);
	}

	@PostMapping("/getuser")
	public ResponseEntity<Staff> getUser(@RequestBody TokenPojo body) {

		try {
			System.out.println(body);

			String user = jwtUtil.validateTokenAndRetrieveSubject(body.getToken());

			System.out.println(user);
			Staff userToken = staffService.getStaffById(userRepo.findByUserName(user).get().getUserId());

			return new ResponseEntity<>(userToken, HttpStatus.ACCEPTED);
		} catch (Exception authExc) {
			authExc.printStackTrace();
//            throw new RuntimeException("Invalid Login Credentials");

		}
		return new ResponseEntity(new ErrorMapper("WRONG CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

}