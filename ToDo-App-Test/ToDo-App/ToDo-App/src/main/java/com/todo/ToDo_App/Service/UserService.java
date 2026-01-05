package com.todo.ToDo_App.Service;

import com.todo.ToDo_App.Repositary.UserRepository;
import com.todo.ToDo_App.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

     public User createUser(User user) {
         userRepository.save(user);
         return user;
     }

      public User getUserById(Long id){
         return userRepository.findById(id).orElseThrow(()-> new RuntimeException("UserId Not Found"));
     }
}
