package com.bazra.usermanagement.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bazra.usermanagement.repository.AccountRepository;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.signin.SignInResponse;
import com.bazra.usermanagement.signup.SignUpRequest;
import com.bazra.usermanagement.signup.SignUpResponse;
import com.bazra.usermanagement.signup.UpdateRequest;
import com.bazra.usermanagement.signup.UpdateResponse;

/**
 * User information service
 * 
 * @author Bemnet
 * @version 4/2022
 *
 */
@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AccountService accountservice;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserInfo userInfo = userRepository.findByUsername(email).get();
        String passwor = userInfo.getPassword();

        return new User(email, passwor, new ArrayList<>());

    }
    
    private boolean checkname(String input) {
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean lowerCasePresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (Character.isLowerCase(currentCharacter)) {
                lowerCasePresent = true;
            }
        }

        return !numberPresent && upperCasePresent && lowerCasePresent;
    }

    private boolean checkString(String input) {
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean lowerCasePresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (Character.isLowerCase(currentCharacter)) {
                lowerCasePresent = true;
            }
        }

        return numberPresent && upperCasePresent && lowerCasePresent;
    }

    /**
     * check of password validity
     * 
     * @param str
     * @return
     */
    public ResponseEntity<?> Validation(SignUpRequest request) {

        return ResponseEntity.badRequest().body(new SignUpResponse("Error: Invalid Password"));
    }

    /**
     * validates field for user signup
     * 
     * @param userInfo
     * @return signup status
     */
    
    
    static String NumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "0123456789";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
    public ResponseEntity<?> signUpUser(UserInfo userInfo, String pass1) {
        boolean userExists = userRepository.findByUsername(userInfo.getUsername()).isPresent();
        boolean userExist = userRepository.findByEmail(userInfo.getEmail()).isPresent();
        if (userExists) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Error: Username is already taken!"));
        } else if (userExist) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Error: Email already in use!"));
        }

        if (!checkString(pass1)) {
            return ResponseEntity.badRequest().body(
                    new SignUpResponse("Your password must have atleast 1 number, 1 uppercase and 1 lowercase letter"));
        } else if (pass1.chars().filter(ch -> ch != ' ').count() < 4
                || pass1.chars().filter(ch -> ch != ' ').count() > 8) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Your password must have 8 to 15 characters "));
        }

        if ((checkname(userInfo.getFirstname()) || checkname(userInfo.getLastname()))) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Invalid name values"));
        }
        if (userInfo.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your email "));
        }
        if (userInfo.getFirstname().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your name "));
        }
        if (userInfo.getLastname().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your father name "));
        }
        if (userInfo.getUsername().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your phone "));
        }
        if (userInfo.getCountry().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your country "));
        }
        
        userRepository.save(userInfo);
        UserInfo userInfo2 = userRepository.findByUsername(userInfo.getUsername()).get();
        Account account = new Account();
        String accountnumber=NumericString(8);
        if(accountRepository.findByAccountNumberEquals(accountnumber)!=null) {
            return ResponseEntity.badRequest().body(new ResponseError("Error"));
        }
        account.setAccountNumber(userInfo2.getUsername());
        account.setUser_id(userInfo2.getId());
        account.setUsername(userInfo2.getUsername());
        account.setBalance(new BigDecimal(1000));
        accountservice.save(account);
        return ResponseEntity.ok(new SignUpResponse(userInfo2.getUsername(), userInfo2.getRoles(),
                userInfo2.getCountry(), userInfo2.getGender(), "Successfully Registered", userInfo2.getFirstname(),
                userInfo2.getLastname()));
    }

    /**
     * update password
     * 
     * @param request
     * @param newpassword
     * @return update status
     */
    public ResponseEntity<?> updatePassword(UpdateRequest request, String newpassword, String onldpass) {
        UserInfo userInfo = userRepository.findByUsername(request.getUsername()).get();

        userInfo.setPassword(newpassword);
        userRepository.save(userInfo);

        return ResponseEntity.ok(new UpdateResponse("successfully updated password"));

    }

    /**
     * checking for registered user
     * 
     * @param username
     * @param jwt
     * @return user
     */
    public ResponseEntity<?> signInUser(UserDetails username, String jwt) {
        UserInfo userInfo = userRepository.findByUsername(username.getUsername()).get();
        boolean userExists = userRepository.findByUsername(userInfo.getUsername()).isPresent();

        if (!userExists) {
            System.out.println("Wrong username or password");
        }

        return ResponseEntity.ok(new SignInResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getRoles(),
                userInfo.getCountry(), userInfo.getGender(), jwt));
    }

}
