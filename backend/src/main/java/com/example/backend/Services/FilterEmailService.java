package com.example.backend.Services;
import com.example.backend.DTO.ReceiverDTO;
import org.springframework.stereotype.Service;
import com.example.backend.Entities.Priority;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import com.example.backend.DTO.FilterDTO;
import java.time.format.DateTimeFormatter;

class DateFilter {
    public List<ReceiverDTO> filter(List<ReceiverDTO> receiverList, String startDate, String endDate) {
        if (startDate.equals("") || endDate.equals("")) {
            return receiverList;
        }
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);

        return receiverList.stream()
                .filter(receiver -> receiver.getEmail() != null && receiver.getEmail().getMetadata() != null)
                .filter(receiver -> {
                    LocalDateTime dateSent = receiver.getEmail().getMetadata().getDateSent();
                    return (startDate == null || dateSent.isAfter(startDateTime)) &&
                            (endDate == null || dateSent.isBefore(endDateTime));
                })
                .map(receiver -> new ReceiverDTO(receiver))  // Deep copy here
                .collect(Collectors.toList());
    }
}


class SubjectFilter {
    public List<ReceiverDTO> filter(List<ReceiverDTO> receiverList, List<String> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return receiverList;
        }

        return receiverList.stream()
                .filter(receiver -> receiver.getEmail() != null)
                .filter(receiver -> subjects.contains(receiver.getEmail().getSubject()))
                .map(receiver -> new ReceiverDTO(receiver))  // Deep copy here
                .collect(Collectors.toList());
    }
}


class ReceiversFilter {
    public List<ReceiverDTO> filter(List<ReceiverDTO> receiverList, List<String> emailAddresses) {
        if (emailAddresses == null || emailAddresses.isEmpty()) {
            return receiverList;
        }

        return receiverList.stream()
                .filter(receiver -> receiver.getReceivers() != null)
                .filter(receiver -> receiver.getReceivers().stream()
                        .anyMatch(user -> emailAddresses.contains(user.getEmailAddress())))
                .map(receiver -> new ReceiverDTO(receiver))  // Deep copy here
                .collect(Collectors.toList());
    }
}


class SendersFilter {
    public List<ReceiverDTO> filter(List<ReceiverDTO> receiverList, List<String> emailAddresses) {
        if (emailAddresses == null || emailAddresses.isEmpty()) {
            return receiverList;
        }

        return receiverList.stream()
                .filter(receiver -> receiver.getEmail() != null && receiver.getEmail().getSender() != null)
                .filter(receiver -> emailAddresses.contains(receiver.getEmail().getSender().getEmailAddress()))
                .map(receiver -> new ReceiverDTO(receiver))  // Deep copy here
                .collect(Collectors.toList());
    }
}

class PriorityFilter {
    public List<ReceiverDTO> filter(List<ReceiverDTO> receiverList, String priorityString) {
        if (priorityString == null || priorityString.isEmpty()) {
            return receiverList;
        }

        return receiverList.stream()
                .filter(receiver -> receiver.getEmail() != null && receiver.getEmail().getMetadata() != null)
                .filter(receiver -> {
                    Priority priority = receiver.getEmail().getMetadata().getPriority();
                    return priorityString.equalsIgnoreCase(priority.name());
                })
                .map(receiver -> new ReceiverDTO(receiver))  // Deep copy here
                .collect(Collectors.toList());
    }
}


class BodyFilter {
    public List<ReceiverDTO> filter(List<ReceiverDTO> receiverList, String bodySubstring) {
        if (bodySubstring == null || bodySubstring.isEmpty()) {
            return receiverList;
        }

        return receiverList.stream()
                .filter(receiver -> receiver.getEmail() != null && receiver.getEmail().getBody() != null)
                .filter(receiver -> receiver.getEmail().getBody().contains(bodySubstring))
                .map(receiver -> new ReceiverDTO(receiver))  // Deep copy here
                .collect(Collectors.toList());
    }
}

class AttachmentsFilter {

}

class TrashedFilter {
    public List<ReceiverDTO> removeTrashed(List<ReceiverDTO> receiverList, long userId) {
        List<ReceiverDTO> filteredList = new ArrayList<>();
        for (ReceiverDTO receiver : receiverList) {
            if (receiver.getEmail().getSender().getUserId() == userId) {
                if (receiver.getEmail().getMetadata().getIsTrashed() == null || !receiver.getEmail().getMetadata().getIsTrashed()) {
                    filteredList.add(new ReceiverDTO(receiver)); // Deep copy
                }
            } else if (receiver.getIsTrashed() == null || !receiver.getIsTrashed()) {
                filteredList.add(new ReceiverDTO(receiver)); // Deep copy
            }
        }
        return filteredList;
    }

    public List<ReceiverDTO> getTrashed(List<ReceiverDTO> receiverList, long userId) {
        List<ReceiverDTO> filteredList = new ArrayList<>();
        for (ReceiverDTO receiver : receiverList) {
            if (receiver.getEmail().getSender().getUserId() == userId) {
                if (receiver.getEmail().getMetadata().getIsTrashed() != null && receiver.getEmail().getMetadata().getIsTrashed()) {
                    filteredList.add(new ReceiverDTO(receiver)); // Deep copy
                }
            } else if (receiver.getIsTrashed() != null && receiver.getIsTrashed()) {
                filteredList.add(new ReceiverDTO(receiver)); // Deep copy
            }
        }
        return filteredList;
    }
}

class StarredFilter {
    public List<ReceiverDTO> removeStarred(List<ReceiverDTO> receiverList, long userId) {
        List<ReceiverDTO> filteredList = new ArrayList<>();
        for (ReceiverDTO receiver : receiverList) {
            if (receiver.getIsStarred() == null || !receiver.getIsStarred()) {
                filteredList.add(new ReceiverDTO(receiver)); // Deep copy
            }
        }
        return filteredList;
    }

    public List<ReceiverDTO> getStarred(List<ReceiverDTO> receiverList, long userId) {
        List<ReceiverDTO> filteredList = new ArrayList<>();
        for (ReceiverDTO receiver : receiverList) {
            if (receiver.getIsStarred() != null && receiver.getIsStarred()) {
                filteredList.add(new ReceiverDTO(receiver)); // Deep copy
            }
        }
        return filteredList;
    }
}


@Service
public class FilterEmailService {
    TrashedFilter trashedFilter;
    StarredFilter starredFilter;

    FilterEmailService(){
        this.trashedFilter = new TrashedFilter();
        this.starredFilter = new StarredFilter();
    }

    public List<ReceiverDTO> removeTrash(List<ReceiverDTO> emails, Long userId){
        return trashedFilter.removeTrashed(emails, userId);
    }

    public List<ReceiverDTO> getTrash(List<ReceiverDTO> emails, Long userId){
        return trashedFilter.getTrashed(emails, userId);
    }

    public List<ReceiverDTO> getStarred(List<ReceiverDTO> emails, Long userId){
        return starredFilter.getStarred(emails, userId);
    }

    public List<ReceiverDTO> filter(List<ReceiverDTO> emails, FilterDTO filterDTO){
        DateFilter dateFilter = new DateFilter();
        SubjectFilter subjectFilter = new SubjectFilter();
        ReceiversFilter receiversFilter = new ReceiversFilter();
        SendersFilter sendersFilter = new SendersFilter();
        PriorityFilter priorityFilter = new PriorityFilter();
        BodyFilter bodyFilter = new BodyFilter();

        List<ReceiverDTO> filteredEmails = dateFilter.filter(emails, filterDTO.getStartDate(), filterDTO.getEndDate());
        filteredEmails = subjectFilter.filter(filteredEmails, filterDTO.getSubjects());
        filteredEmails = receiversFilter.filter(filteredEmails, filterDTO.getReceivers());
        filteredEmails = sendersFilter.filter(filteredEmails, filterDTO.getSenders());
        filteredEmails = priorityFilter.filter(filteredEmails, filterDTO.getPriority());
        filteredEmails = bodyFilter.filter(filteredEmails, filterDTO.getBody());

        return filteredEmails;
    }
}

