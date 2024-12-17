package com.example.backend.Services;//package com.example.backend.Services;
//
//import com.example.backend.Entities.Email;
//import com.example.backend.Entities.FolderOwner;
//import com.example.backend.Repositories.EmailRepository;
//import com.example.backend.Repositories.FolderMemberRepository;
//import com.example.backend.Repositories.FolderOwnerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FolderService {
//
//    private final FolderOwnerRepository folderOwnerRepository;
//    private final FolderMemberRepository folderMemberRepository;
//    private final EmailRepository emailRepository;
//
//    // Constructor Injection
//    public FolderService(FolderOwnerRepository folderOwnerRepository,
//                         FolderMemberRepository folderMemberRepository,
//                         EmailRepository emailRepository) {
//        this.folderOwnerRepository = folderOwnerRepository;
//        this.folderMemberRepository = folderMemberRepository;
//        this.emailRepository = emailRepository;
//    }
//
//    public List<FolderOwner> getFoldersByUser(Long userId) {
//        return folderOwnerRepository.findByUser_Id(userId);
//    }
//
//    public List<Email> getEmailsByFolderAndFilters(Long folderId, Long userId, String subject) {
//        return emailRepository.findByFolder_IdAndReceivers_User_IdAndSubject(folderId, userId, subject);
//    }
//}