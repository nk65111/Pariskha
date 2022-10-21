package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/{role}")
    public User createUser(@RequestBody User user,@PathVariable("role") String r) throws Exception {
        user.setProfile("default.png");
        UserRole userRole=new UserRole();
        Role role=new Role();
        role.setRoleId(44L);
        role.setRoleName(r);

        userRole.setRole(role);
        userRole.setUser(user);
        Set<UserRole> userRoleSet=new HashSet<>();
        userRoleSet.add(userRole);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser=  this.userService.createUser(user,userRoleSet);
        return  savedUser;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
       return  this.userService.getUser(username);
    }

    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable("userid") Long userid){
       this.userService.deleteUser(userid);
    }


}
