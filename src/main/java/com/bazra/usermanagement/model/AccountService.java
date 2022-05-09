package com.bazra.usermanagement.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bazra.usermanagement.repository.AccountRepository;
import com.bazra.usermanagement.repository.TransactionRepository;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.request.DepositRequest;
import com.bazra.usermanagement.request.TransferRequest;
import com.bazra.usermanagement.request.WithdrawRequest;
import com.bazra.usermanagement.response.DepositResponse;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.TransferResponse;
import com.bazra.usermanagement.response.WithdrawResponse;
import com.bazra.usermanagement.util.JwtUtil;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    TransactionRepository transactionRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
   
    Account toAccount;
    Account fromAccount;

    public Account save(Account account) {
        accountRepository.save(account);
        return accountRepository.findByAccountNumberEquals(account.getAccountNumber());
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    
    public List<Transaction> findall(String accountnumber){
//        List<Transaction> transactions = transactionRepository.findByAccountNumberEquals(accountnumber);
        return transactionRepository.findAll();
    }

    public Account findByAccountNumber(String accountnumber) {
        Account account = accountRepository.findByAccountNumberEquals(accountnumber);
        return account;
    }
    
    public ResponseEntity<?> sendMoney(TransferRequest transferBalanceRequest,String name) {

        String toAccountNumber = transferBalanceRequest.getToAccountNumber();
        BigDecimal amount = transferBalanceRequest.getAmount();
        fromAccount= accountRepository.findByAccountNumberEquals(name);
        toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);
        if (toAccount == null) {
            return ResponseEntity.badRequest().body(new ResponseError("Invalid receiver"));
        }
        if (amount.compareTo(new BigDecimal(5)) == -1) {
            return ResponseEntity.badRequest().body(new ResponseError("Minimum amount to transfer is 5"));
        }
        if (transferBalanceRequest.getMessage() == null) {
            return ResponseEntity.badRequest().body(new ResponseError("Enter your remark"));
        }
        if (fromAccount.getBalance().compareTo(BigDecimal.ONE) == 1
                && fromAccount.getBalance().compareTo(amount) == 1) {
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            accountRepository.save(fromAccount);
            toAccount.setBalance(toAccount.getBalance().add(amount));
            accountRepository.save(toAccount);
            Transaction transaction = transactionRepository
                    .save(new Transaction(toAccountNumber, amount, Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS)),
                            "Transfer successful! " + amount + "$ has been sent to " + toAccountNumber));
            return ResponseEntity.ok(new TransferResponse(transaction.getAccountNumber(), transaction.getTransactionAmount(),
                    transaction.getTransactionDateTime(), transaction.getMessage()));
        }
        return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
    }

    public ResponseEntity<?> withdraw(WithdrawRequest withdraw,String name) {
        
        Account agAccount = accountRepository.findByAccountNumberEquals(name);
        Account frAccount = accountRepository.findByAccountNumberEquals(withdraw.getFromAccountNumber());
        BigDecimal balance = frAccount.getBalance();
        if (balance.compareTo(withdraw.getAmount()) == 1) {
            frAccount.setBalance(balance.subtract(withdraw.getAmount()));
            accountRepository.save(frAccount);
            agAccount.setBalance(agAccount.getBalance().add(withdraw.getAmount()));
            accountRepository.save(agAccount);
            Transaction transaction = transactionRepository
                    .save(new Transaction(withdraw.getAmount(), Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS)),
                            "Amount " + withdraw.getAmount() + "$ has been debited from your account. Your current balance is "+balance.subtract(withdraw.getAmount())));
            return ResponseEntity.ok(new WithdrawResponse(transaction.getTransactionAmount(),
                    transaction.getTransactionDateTime(), transaction.getMessage()));
        }

        return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
    }
    
public ResponseEntity<?> Deposit(DepositRequest depositRequest,String name) {
        
        Account agAccount = accountRepository.findByAccountNumberEquals(name);
        Account toAccount = accountRepository.findByAccountNumberEquals(depositRequest.getToaccountnumber());
       
        BigDecimal balance = agAccount.getBalance();
        if (balance.compareTo(depositRequest.getAmount()) == 1) {
            agAccount.setBalance(balance.subtract(depositRequest.getAmount()));
            accountRepository.save(agAccount);
            toAccount.setBalance(toAccount.getBalance().add(depositRequest.getAmount()));
            accountRepository.save(toAccount);
            Transaction transaction = transactionRepository
                    .save(new Transaction(depositRequest.getAmount(), Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS)),
                            "Amount " + depositRequest.getAmount() + "$ has been credited to your account. Your current balance is "+toAccount.getBalance().add(depositRequest.getAmount())));
            return ResponseEntity.ok(new DepositResponse(transaction.getTransactionAmount(),
                    transaction.getTransactionDateTime(), transaction.getMessage()));
        }

        return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
    }
//    
//    public AccountStatement getStatement(String accountnumber) {
//        Account account = accountRepository.findByAccountNumberEquals(accountnumber);
//        return new AccountStatement(account.getBalance(),transactionRepository.findByAccountNumberEquals(accountnumber));
//    }

}
