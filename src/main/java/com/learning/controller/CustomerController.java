package com.learning.controller;

import com.learning.entity.Login;
import com.learning.entity.Account;
import com.learning.entity.Admin;
import com.learning.entity.Beneficary;
import com.learning.entity.Customer;
import com.learning.entity.Transaction;
import com.learning.entity.User;
import com.learning.enums.PaymentType;
import com.learning.enums.RoleType;

import com.learning.enums.Status;
import com.learning.others.UsernamePassword;
import com.learning.pojo.CustomerRequestUpdate;
import com.learning.pojo.ErrorMapper;

import com.learning.repo.UserRepo;

import com.learning.pojo.TokenPojo;
import com.learning.repo.UserRepo;
import com.learning.securtiy.JWTUtil;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
import com.learning.service.CustomerService;
import com.learning.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customer")

public class CustomerController {


    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private CustomerService customerService;
    @Autowired 
    private AccountService accountService;
    
    @Autowired
	private UserRepo userRepo;
    
    @Autowired 
    private TransactionService transactionService;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> authenticate(@RequestBody UsernamePassword body) {

		try {
			//System.out.println(body);
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getUsername(), body.getPassword());

			authManager.authenticate(authInputToken);

			RoleType role = userRepo.findByUserName(body.getUsername()).get().getRole();
			System.out.println(role);

			String token = jwtUtil.generateToken(body.getUsername());
			System.out.println(token);

			return new ResponseEntity<>(Collections.singletonMap("jwt", token), HttpStatus.ACCEPTED);
		} catch (AuthenticationException authExc) {

			System.out.println("WRONG PASSWORD OR USERNAME");
			authExc.printStackTrace();
			return new ResponseEntity(new ErrorMapper("WRONG USERNAME OR PASSWORD"), HttpStatus.BAD_REQUEST);
		}
	}
			
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {

		customer.setStatus(Status.ENABLE);
		customer.setRole(RoleType.CUSTOMER);
		return new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.valueOf(201));
	}

	@GetMapping("/{customerId}/beneficiary")
	public ResponseEntity<List<List<Beneficary>>> getBeneficiary(@PathVariable(name = "customerId") int customerId) {
		try {
			Customer customer = customerService.getCustomerById(customerId);
			List<Account> account = customer.getAccount();
			List<List<Beneficary>> ben1 = new ArrayList<List<Beneficary>>();
			for (Account acc : account) {
				ben1.add(acc.getBeneficary());
			}
			return new ResponseEntity<List<List<Beneficary>>>(ben1, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<List<Beneficary>>>(new ArrayList<List<Beneficary>>(), HttpStatus.OK);
		}
	}

//	@GetMapping("/{customerId}/transaction")
//	public ResponseEntity<List<List<Beneficary>>> getApprovedBeneficiary(
//			@PathVariable(name = "customerId") int customerId) {
//		try {
//			Customer customer = customerService.getCustomerById(customerId);
//			List<Account> account = customer.getAccount();
//			List<List<Beneficary>> ben1 = new ArrayList<List<Beneficary>>();
//			for (Account acc : account) {
//				ben1.add(acc.getBeneficary());
//			}
//			return new ResponseEntity<List<List<Beneficary>>>(ben1, HttpStatus.OK);
//		} catch (NoSuchElementException e) {
//			return new ResponseEntity<List<List<Beneficary>>>(new ArrayList<List<Beneficary>>(), HttpStatus.OK);
//		}
//	}
	
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

	@PostMapping("/{customerID}/account")
	public ResponseEntity createAccount(@PathVariable(name = "customerID") int customerID,
			@RequestBody Account acc) {

		Customer customer;
		try {
			customer = customerService.getCustomerById(customerID);
			//int customerID=customer.getUserId();
			//customer1 = customerService.getCustomerById(customerID);
			customer.addAccount(acc);
			acc.setDateOfCreation(new Date());
			acc.setStatus(Status.DISABLE);
			acc.setApproved(false);
			// accountService.addAccount(acc);
			customerService.updateCustomer(customer);
		}

		// return new ResponseEntity<Customer>(customer, HttpStatus.OK);}
		catch (NullPointerException e) {
			// return new ResponseEntity<Customer>(new Customer(), HttpStatus.valueOf(403));
			acc = null;
		}

		return acc == null ? new ResponseEntity(new ErrorMapper("Account cannot be created"), HttpStatus.OK)
				: new ResponseEntity(acc, HttpStatus.OK);
	}

	

	@GetMapping("/{customerID}/account")
	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable(name = "customerID") int customerID) {
		
			Customer cust = customerService.getCustomerById(customerID);
			System.out.println("I was here");
			if(cust.getAccount() != null) {
				System.out.println(cust.getAccount() == null);
				System.out.println(cust.getAccount());
				return new ResponseEntity<List<Account>>(cust.getAccount(), HttpStatus.valueOf(200));}
			else {
				System.out.println("In else block");
			return new ResponseEntity(new ErrorMapper("There are no accounts created for you!!"), HttpStatus.BAD_REQUEST);}
		

	}

	@GetMapping("/{customerID}")
	public ResponseEntity getCustomer(@PathVariable(name = "customerID") int customerID) {
		Customer customer;
		try {
			// return new
			// ResponseEntity<Customer>(customerService.getCustomerById(customerID),HttpStatus.valueOf(200));
			customer = customerService.getCustomerById(customerID);
		} catch (Exception e) {
			// return new ResponseEntity<Customer>(new Customer(),HttpStatus.NOT_FOUND);
			customer = null;
		}
		return customer == null
				? new ResponseEntity(new ErrorMapper("Sorry, Customer with ID " + customerID + " Not Found"),
						HttpStatus.NOT_FOUND)
				: new ResponseEntity(customer, HttpStatus.OK);
	}

	@PutMapping("/{customerID}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(name = "customerID") int customerID,
			@RequestBody CustomerRequestUpdate cust) {
		Customer customer;
		System.out.print(cust);
		try {
			customer = customerService.getCustomerById(customerID);
			customer.setFullName(cust.getFullName());
			customer.setStatus(cust.getStatus());
			customer.setSecret_question(cust.getSecret_question());
			if(cust.getSecret_answer()!=customer.getSecret_answer())
				customer.setSecret_answer(bCryptPasswordEncoder.encode(cust.getSecret_answer()));
			customer.setPhoneNumber(cust.getPhoneNumber());

			customerService.updateCustomer(customer);}
			//return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.valueOf(200));}
		catch(NoSuchElementException e) {
			//return new ResponseEntity<Customer>(new Customer(), HttpStatus.NOT_FOUND);
			customer=null;
		}
		// return new ResponseEntity<Customer>(customerService.updateCustomer(customer),
		// HttpStatus.valueOf(200));}
		//catch (NoSuchElementException e) {
			// return new ResponseEntity<Customer>(new Customer(), HttpStatus.NOT_FOUND);
			//customer = null;
		//}
		return customer == null
				? new ResponseEntity(new ErrorMapper("Sorry, Customer with ID " + customerID + " Not Found"),
						HttpStatus.NOT_FOUND)
				: new ResponseEntity(customer, HttpStatus.OK);
	}
    
    /*@PostMapping("/{customerID}/beneficiary")
    public ResponseEntity<String> addBeneficary(@PathVariable (name = "customerID") int customerID, @RequestBody Beneficary ben) {
    	try{
    		Account account = accountService.getAccountById(ben.getAccountNumber());
    		Customer customer = customerService.getCustomerById(customerID);
    		account.addbeneficiary(ben);
    		accountService.updateAccount(account);
    		return new ResponseEntity<String>("Beneficiary with account ID"+ ben.getAccountNumber() + " added", HttpStatus.valueOf(200));}
    		
    	catch(NoSuchElementException e) {
    		return new ResponseEntity<String>("Sorry beneficiary with account ID "+ben.getAccountNumber()+" not added", HttpStatus.NOT_FOUND);
    	}
    }*/
    
    /*@DeleteMapping("/{customerID}/beneficary/{beneficaryID}")
    public ResponseEntity<String> deleteBeneficary(@PathVariable (name = "customerID") int customerID, @PathVariable (name = "beneficaryID") int beneficaryID){
    	Customer customer = customerService.getCustomerById(customerID);
    	List<Account> account = customer.getAccount();
    	Beneficary ben1 = beneficiaryService.getBeneficiaryById(beneficaryID);
    	int temp=0;
    	for(Account acc: account) {
    		if(acc.getAccountNumber()==ben1.getAccountNumber()) {
    			acc.remove(ben1);
    			beneficiaryService.deleteBeneficiary(beneficaryID);
    			temp=1;
    			return new ResponseEntity<String>("Beneficiary Deleted", HttpStatus.OK);
    		}
    	}
    	if(temp==0) {
    	return new ResponseEntity<String>("Customer not found", HttpStatus.BAD_REQUEST);
    	}
    	return null;
    }*/
    
    /*@PostMapping("/transfer")
    public ResponseEntity<String> transferAmount(@RequestBody Transaction transaction){
    	transaction.setDate(new Date());
    	Account fromAcc = null;
    	Account toAcc = null;
    	List<Account> allAccounts = accountService.getAllAccount();
    	for(Account acc: allAccounts) {
    		if(acc.getAccountNumber()==transaction.getFromAcc()) {
    			fromAcc = acc;
    		}
    	}
    	for(Account acc: allAccounts) {
    		if(acc.getAccountNumber()==transaction.getToAcc()) {
    			toAcc = acc;
    		}
    	}
    	if(fromAcc == null || toAcc == null) {
    		return new ResponseEntity<String>("From/To Account Number isn't valid", HttpStatus.NOT_FOUND);
    	}
    	if(fromAcc.getAccountBalance()<transaction.getAmount()) {
    		return new ResponseEntity<String>("Transfer amount is more than the amount present in "+fromAcc.getAccountNumber(), HttpStatus.INSUFFICIENT_SPACE_ON_RESOURCE);
    	}
    	fromAcc.setAccountBalance(fromAcc.getAccountBalance()-transaction.getAmount());
    	toAcc.setAccountBalance(toAcc.getAccountBalance()+transaction.getAmount());
    	
    	
    	//accountService.updateAccount(fromAcc);
    	//accountService.updateAccount(toAcc);
    	
    	Transaction trans = transactionService.addTransaction(transaction);
    	fromAcc.addtransaction(trans);
    	toAcc.addtransaction(trans);
    	
    	accountService.updateAccount(fromAcc);
    	accountService.updateAccount(toAcc);
    	return new ResponseEntity<String>("Amount is transfered ",HttpStatus.valueOf(200));
    } */
    
    /*@GetMapping("/{username}/forgot/{question}/{answer}")
    public ResponseEntity<String> validateDetailsforSecretKey(@PathVariable (name = "username") String username, @PathVariable (name = "question") String question,@PathVariable (name = "answer") String answer ) {
    	List<Customer> customer = customerService.getAllCustomer();
    	for(Customer custom:customer) {
    		if((custom.getUserName()==username) && custom.getSecret_question()==question && custom.getSecret_answer()==answer) {
    			return new ResponseEntity<String>("Details Validated",HttpStatus.valueOf(200));
    		}
    	}
    	return new ResponseEntity<String>("Sorry your secret details are not matching",HttpStatus.valueOf(200));
    }
    
    @PostMapping("/token")
	public String generateToken(@RequestBody Login login) {
		System.out.println("The username entered is " + login.getUsername() + " and the password is " + login.getPassword());
		
		return customerService.getToken(login.getUsername(), login.getPassword());
		
	}*/
    
    @PostMapping("/login")
	public User login(@RequestBody User user) {
		User user1 = userRepo.findByUserNameAndPassWord(user.getUserName(), user.getPassWord());
		return user1;}

	@PostMapping("/{customerID}/beneficiary")
	public ResponseEntity<Account> addBeneficary(@PathVariable(name = "customerID") int customerID,
			@RequestBody Beneficary ben) {
		try {
			Account account = accountService.getAccountById(ben.getAccountNumber());
			Customer customer = customerService.getCustomerById(customerID);

			ben.setDate(new Date());
			ben.setIsApproved("false");
			ben = new Beneficary(ben.getAccountNumber(), ben.getAccountType(),ben.getIsApproved() );
			account.addbeneficiary(ben);
			System.out.println(ben);
			account = accountService.updateAccount(account);
			return new ResponseEntity<Account>(account,
					HttpStatus.valueOf(200));
		}

		catch (NoSuchElementException e) {
			return new ResponseEntity("Sorry beneficiary with account ID " + ben.getAccountNumber() + " not added", HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity("Sorry beneficiary with account ID "+ ben.getAccountNumber() + " not added", HttpStatus.NOT_FOUND);
	}}

	@PreAuthorize("hasAuthority('CUSTOMER')")
	@DeleteMapping("/{customerID}/beneficiary/{beneficaryID}")
	public ResponseEntity deleteBeneficary(@PathVariable(name = "customerID") int customerID,
			@PathVariable(name = "beneficaryID") int beneficaryID) {
		Customer customer = customerService.getCustomerById(customerID);
		List<Account> account = customer.getAccount();
		Beneficary ben1 = beneficiaryService.getBeneficiaryById(beneficaryID);
		int temp = 0;
		for (Account acc : account) {
			if (acc.getAccountNumber() == ben1.getAccountNumber()) {
				acc.remove(ben1);
				beneficiaryService.deleteBeneficiary(beneficaryID);
				temp = 1;
				return new ResponseEntity(ben1, HttpStatus.OK);
			}
		}
		if (temp == 0) {
			return new ResponseEntity<String>("Customer not found", HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	@PutMapping("/transfer")
	public ResponseEntity transferAmount(@RequestBody Transaction transaction) {
		transaction.setDate(new Date());
		transaction.setReference("No references required");
		System.out.println("I got hit");
		Account fromAcc = null;
		Account toAcc = null;
		List<Account> allAccounts = accountService.getAllAccount();
		for (Account acc : allAccounts) {
			if (acc.getAccountNumber() == transaction.getFromAcc()) {
				fromAcc = acc;
			}
		}
		for (Account acc : allAccounts) {
			if (acc.getAccountNumber() == transaction.getToAcc()) {
				toAcc = acc;
			}
		}
		if (fromAcc == null || toAcc == null) {
			return new ResponseEntity(new ErrorMapper("From/To Account Number isn't valid"), HttpStatus.NOT_FOUND);
		}
		
		if(fromAcc.getStatus().equals(Status.DISABLE)|| toAcc.getStatus().equals(Status.DISABLE)) {
			return new ResponseEntity(new ErrorMapper("From/To Account Number is disabled, please contact our staff"), HttpStatus.NOT_FOUND);
		}
		if (fromAcc.getAccountBalance() < transaction.getAmount()) {
			return new ResponseEntity(new ErrorMapper(
					"Transfer amount is more than the amount present in " + fromAcc.getAccountNumber()),
					HttpStatus.INSUFFICIENT_SPACE_ON_RESOURCE);
		}
		
		fromAcc.setAccountBalance(fromAcc.getAccountBalance() - transaction.getAmount());
		toAcc.setAccountBalance(toAcc.getAccountBalance() + transaction.getAmount());

		//accountService.updateAccount(fromAcc);
		//accountService.updateAccount(toAcc);
		transaction.setPaymentType(PaymentType.DEBIT);
		Transaction trans = transactionService.addTransaction(transaction);
		
		Transaction t1 = new Transaction(transaction.getReference(),transaction.getAmount(), transaction.getToAcc(), transaction.getFromAcc() , transaction.getReason(), PaymentType.CREDIT);
		Transaction t2 = transactionService.addTransaction(t1);
		fromAcc.addtransaction(transaction);
		toAcc.addtransaction(t2);
		
		System.out.println("Fine till here");
		
		accountService.updateAccount(fromAcc);
		accountService.updateAccount(toAcc);

		return new ResponseEntity(transaction, HttpStatus.valueOf(200));
	}

	//@PreAuthorize("hasAuthority('CUSTOMER')")
	@GetMapping("/{username}/forgot/{question}/{answer}")
	public ResponseEntity<String> validateDetailsforSecretKey(@PathVariable(name = "username") String username,
			@PathVariable(name = "question") String question, @PathVariable(name = "answer") String answer) {
		List<Customer> customer = customerService.getAllCustomer();
		System.out.println(username + question +answer);
		for (Customer custom : customer) {
			System.out.println(custom.getUserName() + custom.getSecret_question() +custom.getSecret_answer());
			if ((custom.getUserName().equals(username)) && custom.getSecret_question().equals(question) 
					&& bCryptPasswordEncoder.matches(answer, custom.getSecret_answer())) {
				System.out.println("Found");
					//&& bCryptPasswordEncoder.matches(answer, custom.getSecret_answer())) {
				return new ResponseEntity<String>("Details Validated", HttpStatus.valueOf(200));
			}
		}
		//return new ResponseEntity(new ErrorMapper("Sorry, your username, security question and answer mismatched. Try again"), HttpStatus.valueOf(200));
		return new ResponseEntity<String>("Sorry your secret details are not matching", HttpStatus.valueOf(200));
	}

	@PostMapping("/getuser")
	public ResponseEntity<Customer> getUser(@RequestBody TokenPojo body) {
		System.out.println("Inside get user");
		try {
			System.out.println(body);

			String user = jwtUtil.validateTokenAndRetrieveSubject(body.getToken());

			System.out.println(user);
			Customer userToken = customerService.getCustomerById(userRepo.findByUserName(user).get().getUserId());

			return new ResponseEntity<>(userToken, HttpStatus.ACCEPTED);
		} catch (Exception authExc) {
			authExc.printStackTrace();

//            throw new RuntimeException("Invalid Login Credentials");

		}
		return new ResponseEntity(new ErrorMapper("WRONG CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getuserID")
	public int getuserID(@RequestBody Customer customer) {
		return customer.getUserId();
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<Customer> updatePassword(@RequestBody UsernamePassword body) {
		List<Customer> customer = customerService.getAllCustomer();
		Customer customer1= new Customer();
		try {
			
				System.out.println(body);
				String username = body.getUsername();

				//List<Customer> customer = customerService.getAllCustomer();
				for(Customer custom:customer) {
					if(username.equals(custom.getUserName())){
						custom.setPassWord(bCryptPasswordEncoder.encode(body.getPassword()));
						customerService.updateCustomer(custom);
						customer1=custom;
					}
				}
				
			} catch (NoSuchElementException e) {
				System.out.println("WRONG USERNAME");
				customer1=null;
			}
		return customer1 == null
				? new ResponseEntity(new ErrorMapper("Sorry, Customer with username " + body.getUsername() + " Not Found"),
						HttpStatus.NOT_FOUND)
				: new ResponseEntity(customer1, HttpStatus.OK);	        
		}
	
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
					if((account.getAccountNumber()==trans.getFromAcc() && trans.getPaymentType().equals(PaymentType.DEBIT))|| (account.getAccountNumber()==trans.getToAcc()&& trans.getPaymentType().equals(PaymentType.CREDIT))){
						finalTransaction.add(trans);
					}
				}
			
			//}
			return new ResponseEntity<List<Transaction>>(finalTransaction, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Transaction>>(new ArrayList<Transaction>(), HttpStatus.OK);
		}
	}
	
	
}
