package com.example.backend.Services;//package com.example.backend.Services;
//import com.example.backend.Entities.User;
//import com.example.backend.Repositories.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    // Add a new user
//    public User addUser(User user) {
//        return userRepository.save(user);
//    }
//
//    // Retrieve a user by ID
//    public Optional<User> getUserById(Long userId) {
//        return userRepository.findById(userId);
//    }
//}