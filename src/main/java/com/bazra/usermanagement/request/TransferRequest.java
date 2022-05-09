package com.bazra.usermanagement.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
//    @NotBlank(message = "Enter your account")
//    private String fromAccountNumber;
    
    @NotBlank(message = "Enter receiving account")
    private String toAccountNumber;
    
    @NotBlank(message = "Minimum amount for transfer is 5")
    private BigDecimal amount;
    
    @NotBlank(message = "Enter your Remark")
    private String message;
    
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getFromAccountNumber() {
//        return fromAccountNumber;
//    }
//
//    public void setFromAccountNumber(String fromAccountNumber) {
//        this.fromAccountNumber = fromAccountNumber;
//    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    
    

}