package com.example.SignUP.service;

import com.example.SignUP.entity.User;
import com.example.SignUP.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {
    //Creating user
    public User creatUser(User user, Set<UserRole> userRoles) throws Exception;

    //get users
    public Set<User> getUsers();

    //get user by username
    public User getUser(String username);


    //delete user by id
    public void deleteUser(Long userId);

    public  User updateUser(User user);

}
