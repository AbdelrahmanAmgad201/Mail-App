
package com.example.backend.Services;

import com.example.backend.Entities.Email;
import com.example.backend.Entities.User;
import com.example.backend.Repositories.ReciverRepository;
import com.example.backend.Repositories.EmailRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class  ReceiverService{
     private final ReciverRepository receiverRepository;
     private final EmailRepository emailRepository;
     private final UserRepository userRepository;

     public  ReceiverService(ReciverRepository receiverRepository,EmailRepository emailRepository,UserRepository userRepository ){
            this.receiverRepository = receiverRepository;
            this.emailRepository = emailRepository;
            this.userRepository = userRepository;
     }

     public void makeitRead(Long receiver ,Long emailID){
          Email email = emailRepository.findById(emailID);
          User user = userRepository.findById(receiverID);

          Receiver receiver = receiverRepository.findByEmailAndReceiver(email, user);

          receiver.setIsRead(true);
          receiverRepository.save(receiver);
     }
     public void makeitTrash(Long receiver ,Long emailID,Data time ){
          Email email = emailRepository.findById(emailID);
          User user = userRepository.findById(receiverID);

          Receiver receiver = receiverRepository.findByEmailAndReceiver(email, user);

          receiver.setIsTrashed(true);
          receiver.setDateTrashed(time);

          receiverRepository.save(receiver);
     }
     public  List<Email> gitAllEmailsReceiver(Long userID){
          List<Receiver> receivers = receiverRepository.findByReceiver_UserId(userID);
          List<Email> receivedEmails = receivers.stream()
                                                  .map(Receiver::getEmail)
                                                  .distinct()
                                                  .toList();

          return  receivedEmails ;
     }
     public void deleteEmail(){

     }
}