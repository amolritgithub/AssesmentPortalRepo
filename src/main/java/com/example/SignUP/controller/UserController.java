package com.example.SignUP.controller;

import com.example.SignUP.entity.Role;
import com.example.SignUP.entity.User;
import com.example.SignUP.entity.UserRole;
import com.example.SignUP.helper.UserFoundException;
import com.example.SignUP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

//        user.setProfile("default.png");
//        //encoding password with bcryptpasswordencder
//
//        user.setPassword(user.getPassword());

        Set<UserRole> roles=new HashSet<>();
        Role role= new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        return  this.userService.creatUser(user,roles);
    }
    @GetMapping("/")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok(this.userService.getUsers());
    }



    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return  this.userService.getUser(username);
    }

    //delete the user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }

    //update the user
    @PutMapping("/")
    public User updateCategory(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

}
