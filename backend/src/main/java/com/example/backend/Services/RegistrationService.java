package com.example.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.DTO.RegistrationRequest;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Entities.User;
import java.util.Optional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public long login(RegistrationRequest registrationRequest){
        Optional<User> userOptional = userRepository.findByEmailAddress(registrationRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.getPasswordHash().equals(registrationRequest.getPassword()))
                return user.getUserId();
            else throw new RuntimeException("password doesn't match");
        }
        else {
            throw new RuntimeException("email doesn't exist");
        }
    }

    public long signup(RegistrationRequest registrationRequest){
        User user = User.builder()
                .emailAddress(registrationRequest.getEmail())
                .passwordHash(registrationRequest.getPassword()) // Don't forget to hash passwords properly
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .build();

        if (userRepository.findByEmailAddress(user.getEmailAddress()).isPresent()) {
            throw new RuntimeException("Email address already exists: " + user.getEmailAddress());
        }
        User savedUser = userRepository.save(user);
        return savedUser.getUserId();
    }

}
