package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {
     User createUser(User user, Set<UserRole> userRoleSet) throws Exception;
     User getUser(String username);

     void deleteUser(Long userid);
}
