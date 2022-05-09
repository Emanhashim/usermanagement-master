package com.bazra.usermanagement.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.model.UserInfoService;
import com.bazra.usermanagement.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * SignUp Controller
 * 
 * @author Bemnet
 * @version 4/2022
 */

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

public class SignUpUserData {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserRepository userRepository;

    /**
     * Handles user SignUp request
     * 
     * @param request( user input)
     * @return signup validation response
     */
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpRequest request) {
        String pass1 = request.getPassword();
        String pass2 = request.getPassword2();


        if (!pass1.matches(pass2)) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Error: Passwords don't match!"));
        }

        UserInfo user = new UserInfo(request.getFirstname(), request.getLastname(),
                passwordEncoder.encode(request.getPassword()), request.getUsername());
        user.setCountry(request.getCountry());
        user.setBirthday(request.getBirthday());
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());
        
        String strgender = request.getGender();

        if (strgender.matches("MALE")) {

            user.setGender(strgender);

        } else if (strgender.matches("FEMALE")) {

            user.setGender(strgender);
        }

        return userInfoService.signUpUser(user, pass1);
    }
}
