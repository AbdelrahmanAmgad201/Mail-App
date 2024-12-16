
package com.example.backend.Services;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.ReciverRepository;
import com.example.backend.Repositories.EmailRepository;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Repositories.EmailMetadataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class  EmailService{
    private final ReciverRepository receiverRepository;
    private final EmailRepository emailRepository;
    private final UserRepository userRepository;
    private  final  EmailMetadataRepository emailMetadataRepository

    public  ReceiverService(ReciverRepository receiverRepository,EmailRepository emailRepository,UserRepository userRepository , EmailMetadataRepository emailMetadataRepository){
        this.receiverRepository = receiverRepository;
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
    }


        public Long sendEmail(Long senderId, String subject, String body, Long[] recipientIds, List<Attachment> attachments) {
            User sender = userRepository.findById(senderId);

            Email email = Email.builder()
                               .subject(subject)
                               .body(body)
                               .sender(sender)
                               .build();

            email = emailRepository.save(email);

            for (Long i : recipientIds) {
                User recipient = userRepository.findById(i);


                Receiver receiver = Receiver.builder()
                                            .email(email)
                                            .receiver(recipient)
                                            .isRead(false)
                                            .isTrashed(false)
                                            .dateTrashed(null)
                                            .build();
                receiverRepository.save(receiver);
            }

            return email.getEmailId();
        }
        public  List<Email> gitAllEmailsSender(Long userID){
            return emailRepository.findBySender_UserId(userID);
        }
    public void makeitTrash(Long receiver ,Long emailID,Data time ){
        Email email = emailRepository.findById(emailID);
        User user = userRepository.findById(receiverID);

        Receiver receiver = receiverRepository.findByEmailAndReceiver(email, user);

        receiver.setIsTrashed(true);
        receiver.setDateTrashed(time);

        receiverRepository.save(receiver);
    }


        public void updateMetadata(Long emailId, Boolean isTrashed, Date dateTrashed, Priority priority, Boolean isSpam) {
            // Find the email by ID
            Email email = emailRepository.findById(emailId);

            EmailMetadata metadata = email.getMetadata();





            if (priority != null) {
                metadata.setPriority(priority);
            }


            emailMetadataRepository.save(metadata);
        }
    public void updateIsTrashed(Long emailId, Boolean isTrashed, Date dateTrashed) {
        // Find the email by ID
        Email email = emailRepository.findById(emailId);
        EmailMetadata metadata = email.getMetadata();

        metadata.setIsTrashed(isTrashed);
        metadata.setDateTrashed(dateTrashed);

        emailMetadataRepository.save(metadata);
    }

    public void deleteEmail(){

        }
}