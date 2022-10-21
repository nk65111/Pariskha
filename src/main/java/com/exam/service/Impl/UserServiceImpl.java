package com.exam.service.Impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoleSet) throws Exception {
        User createdUser=null;
        try{
            User local=userRepository.findByUsername(user.getUsername());
            if(local!=null){
                System.out.println("User is already Present");
                throw new Exception("User is already Presnt");
            }else{
                System.out.print(user.getEmail());
                for(UserRole userRole :userRoleSet){

                    this.roleRepository.save(userRole.getRole());
                    user.getUserRoles().add(userRole);
                }
                User savedUser=this.userRepository.save(user);
                createdUser=savedUser;
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return createdUser;
    }

    @Override
    public User getUser(String username) {
        return  this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userid) {
        this.userRepository.deleteById(userid);
    }
}
