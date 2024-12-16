package com.example.backend.Services;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.Starred;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.StarredRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarredService {


    private final StarredRepository starredRepository;

    public StarredService(StarredRepository starredRepository) {
        this.starredRepository = starredRepository;
    }

    // Retrieve all Starred email IDs by user ID
    public List<Long> getStarredEmailIdsByUserId(Long userId) {
        return starredRepository.findStarredEmailIdsByUserId(userId);
    }

    // Retrieve all Starred emails by user ID
    public List<Email> getStarredEmailsByUserId(Long userId) {
        return starredRepository.findStarredEmailsByUserId(userId);
    }
}
