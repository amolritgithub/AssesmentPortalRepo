package com.example.SignUP.service.impl;

import com.example.SignUP.dao.RoleRepository;
import com.example.SignUP.dao.UserRepository;
import com.example.SignUP.entity.User;
import com.example.SignUP.entity.UserRole;
import com.example.SignUP.helper.UserFoundException;
import com.example.SignUP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //creting user
    @Override
    public User creatUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is aleready there !!");
            throw new UserFoundException();
        } else {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public Set<User> getUsers() {
        return null;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }



    //delete by userid
    @Override
    public void deleteUser(Long userId) {

        this.userRepository.deleteById(userId);
    }

    //update user
    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }


}

