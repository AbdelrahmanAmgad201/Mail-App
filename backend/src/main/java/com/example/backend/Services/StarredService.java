package com.example.backend.Services;//package com.example.backend.Services;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.Starred;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.EmailRepository;
import com.example.backend.Repositories.StarredRepository;
import com.example.backend.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StarredService {
    private final StarredRepository starredRepository;
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;

    public Optional<Starred> starEmail(long userId, long emailId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Email email = emailRepository.findById(emailId)
                .orElseThrow(() -> new EntityNotFoundException("Email not found"));

        // Check if already starred
        Optional<Starred> existingStarred = starredRepository
                .findByUserAndEmail(userId, emailId);

        if (existingStarred.isPresent()) {
            // If already starred, remove the star (unstar)
            starredRepository.delete(existingStarred.get());
            return Optional.empty();
        }

        // Create new starred entry
        Starred starred = Starred.builder()
                .user(user)
                .email(email)
                .build();

        return Optional.of(starredRepository.save(starred));
    }
}
