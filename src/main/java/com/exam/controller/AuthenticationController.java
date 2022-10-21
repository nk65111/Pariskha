package com.exam.controller;

import com.exam.config.JwtUtil;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.Impl.UserDetailsServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/genrate-token")
    public ResponseEntity<?> genrateToke(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
           authentication(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token =this.jwtUtil.generateToken(userDetails);
//        System.out.println(token+"**********************");
        return  ResponseEntity.ok(new JwtResponse(token));
    }

    public void authentication(String username,String password) throws Exception {
        try {
//            System.out.println("Username==>"+username+"password==>"+password);
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw  new Exception("User Disable "+e.getMessage());
        }catch (BadCredentialsException e){
             throw new Exception("Invalid usename and password ,"+e.getMessage());
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return  (User)this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
