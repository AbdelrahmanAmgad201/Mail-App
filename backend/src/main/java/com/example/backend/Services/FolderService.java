package com.example.backend.Services;

import com.example.backend.Entities.FolderBySubject;
import com.example.backend.Entities.FolderMember;
import com.example.backend.Entities.FolderOwner;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.FolderBySubjectRepository;
import com.example.backend.Repositories.FolderMemberRepository;
import com.example.backend.Repositories.FolderOwnerRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FolderService {

    private final FolderOwnerRepository folderOwnerRepository;
    private final FolderBySubjectRepository folderBySubjectRepository;
    private final FolderMemberRepository folderMemberRepository;
    private final UserRepository userRepository;

    public FolderService(FolderOwnerRepository folderOwnerRepository,
                         FolderBySubjectRepository folderBySubjectRepository,
                         FolderMemberRepository folderMemberRepository,
                         UserRepository userRepository) {
        this.folderOwnerRepository = folderOwnerRepository;
        this.folderBySubjectRepository = folderBySubjectRepository;
        this.folderMemberRepository = folderMemberRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FolderOwner createFolderWithFilters(Long userId, String folderName, List<String> subjectFilters, List<Long> memberIds) {
        // Get the user who owns the folder
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Check if the folder already exists
        FolderOwner folder = folderOwnerRepository.findByFolderNameAndUserId(folderName, userId);
        if (folder == null) {
            folder = FolderOwner.builder()
                    .folderName(folderName)
                    .user(user)
                    .build();
            folder = folderOwnerRepository.save(folder);
        }

        // Add subject filters
        for (String subject : subjectFilters) {
            FolderBySubject folderBySubject = FolderBySubject.builder()
                    .folder(folder)
                    .filterSubject(subject)
                    .build();
            folderBySubjectRepository.save(folderBySubject);
        }

        // Add members to the folder
        for (Long memberId : memberIds) {
            User memberUser = userRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

            FolderMember folderMember = FolderMember.builder()
                    .folder(folder)
                    .user(memberUser)
                    .build();
            folderMemberRepository.save(folderMember);
        }

        return folder;
    }
}
