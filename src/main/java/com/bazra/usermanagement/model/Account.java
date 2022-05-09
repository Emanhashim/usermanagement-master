package com.bazra.usermanagement.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//import com.bazra.usermanagement.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name = "seq", initialValue = 1000, allocationSize = 100)
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private String accountNumber;
    private BigDecimal balance;
    private long user_id;
    private String username;
//  @Column(name="user_id")
//  @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//  private UserInfo userInfo;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    // private AccountTypes accounttype;
//  private Date createdat;
//  private Date updatedat;
//  private Date lastAccessed;
//  private Status status;
//  private AccountDetail accountDetail;
//    public Account() {
//
//    }

    public Account(String balance) {
        this.balance=new BigDecimal(balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String id) {
        this.accountNumber = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

//  public Date getLastAccessed() {
//        return lastAccessed;
//    }
//    public void setLastAccessed(Date lastAccessed) {
//        this.lastAccessed = lastAccessed;
//    }
//    public Date getCreatedat() {
//      return createdat;
//  }
//  public void setCreatedat(Date createdat) {
//      this.createdat = createdat;
//  }
//  public Date getUpdatedat() {
//      return updatedat;
//  }
//  public void setUpdatedat(Date updatedat) {
//      this.updatedat = updatedat;
//  }
//  
//  public AccountTypes getAccounttype() {
//      return accounttype;
//  }
//  public AccountTypes setAccounttype(AccountTypes accounttype) {
//      return  accounttype;
//  }
//  
//    public Status getStatus() {
//        return status;
//    }
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//    public AccountDetail getAccountDetail() {
//        return accountDetail;
//    }
//    public void setAccountDetail(AccountDetail accountDetail) {
//        this.accountDetail = accountDetail;
//    }
//  

}
