package com.example.backend.Services;

import com.example.backend.Entities.DTO.emailsDTO;
import com.example.backend.Entities.emails;
import com.example.backend.Repositories.emailsRepository;
import org.springframework.stereotype.Service;

@Service
public class mailService {
    public final emailsRepository emailsRepo;

    public mailService(emailsRepository emailsRepository){
        this.emailsRepo =emailsRepository;
    }
    public void  saveEmails(emailsDTO emailsDTO){
        emails emailEntity = emailsDTO.toEntity();


        emailsRepo.save(emailEntity);

    }

}
