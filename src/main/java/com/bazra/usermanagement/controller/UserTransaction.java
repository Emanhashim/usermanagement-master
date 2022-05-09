package com.bazra.usermanagement.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.AccountService;
import com.bazra.usermanagement.model.Transaction;
import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.repository.TransactionRepository;
import com.bazra.usermanagement.request.DepositRequest;
import com.bazra.usermanagement.request.TransactionRequest;
import com.bazra.usermanagement.request.TransferRequest;
import com.bazra.usermanagement.request.WithdrawRequest;


@RestController
@RequestMapping("/api/account")
public class UserTransaction {

    @Autowired
    private AccountService accountService;
    @Autowired
    TransactionRepository transactionRepository;
    
//    @Autowired 
//    Account account2;
//    Account account2 =new Account();
//    @PostMapping("/create")
//    public Account create(@RequestBody Accrequest account) {
//        
//        account2.setAccountNumber(account.getAccountNumber());
//        account2.setBalance(account.getBalance());
//        accountService.save(account2);
//        return accountService.findByAccountNumber(account.getAccountNumber());
//    }
    public UserInfo getCurrentUser(@AuthenticationPrincipal UserInfo user) {
        return user;
    }
    @GetMapping("/all")
    public List<Account> all() {
        return accountService.findAll();
    }

    @PostMapping("/test")
    public  Collection<? extends GrantedAuthority> test(@RequestBody TransferRequest transferBalanceRequest, Authentication authentication) {
     return authentication.getAuthorities();
//        return accountService.sendMoney(transferBalanceRequest,authentication.getName());
    }

    @PostMapping("/sendmoney")
    public ResponseEntity<?> sendMoney(@RequestBody TransferRequest transferBalanceRequest, Authentication authentication) {
     
        return accountService.sendMoney(transferBalanceRequest,authentication.getName());
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest withdrawRequest,Authentication authentication) {
        
        return accountService.withdraw(withdrawRequest,authentication.getName());
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest depositRequest,Authentication authentication) {
        
        return accountService.Deposit(depositRequest,authentication.getName());
    }
//    @RolesAllowed("ADMIN")
    @PostMapping("/transaction")
    public List<Transaction> transaction(@RequestBody TransactionRequest transactionRequest) {
        
        return accountService.findall(transactionRequest.getAccountNumber());
    }

//    @PostMapping("/statement")
//    public Response getStatement(
//            @RequestBody Accountrequest accountStatementRequest
//
//    ){
//        return (Response) Response.ok().setPayload(
//                accountService.getStatement(accountStatementRequest.getAccountNumber())
//        );
//
//    }
}