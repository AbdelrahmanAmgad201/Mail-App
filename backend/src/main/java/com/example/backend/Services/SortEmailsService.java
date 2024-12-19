package com.example.backend.Services;

import com.example.backend.DTO.ReceiverDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

// Define SortCommand class
class SortCommand {
    EmailSortingStrategy sortingStrategy; // Made private for encapsulation

    // Method to set the sorting strategy
    public void setCommand(EmailSortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    // Method to execute the strategy
    public List<ReceiverDTO> execute(List<ReceiverDTO> emails) {
        return sortingStrategy.sort(emails);
    }
}

// Define EmailSortingStrategy interface
interface EmailSortingStrategy {
    List<ReceiverDTO> sort(List<ReceiverDTO> emails);
}

// PrioritySortingStrategy implementation
class PrioritySortingStrategy implements EmailSortingStrategy {
    @Override
    public List<ReceiverDTO> sort(List<ReceiverDTO> emails) {
        return emails.stream()
                .sorted(Comparator.comparing((ReceiverDTO receiver) -> receiver.getEmail().getMetadata().getPriority())
                                  .thenComparing(receiver -> receiver.getEmail().getMetadata().getDateSent()))
                .collect(Collectors.toList());
    }
}

// DateSortingStrategy implementation
class DateSortingStrategy implements EmailSortingStrategy {
    @Override
    public List<ReceiverDTO> sort(List<ReceiverDTO> emails) {
        return emails.stream()
                .sorted(Comparator.comparing(receiver -> receiver.getEmail().getMetadata().getDateSent()))
                .collect(Collectors.toList());
    }
}

// Factory class to return the appropriate sorting strategy
class SortStrategyFactory {
    public EmailSortingStrategy getStrategy(String strategy) {
        switch (strategy) {
            case "priority":
                return new PrioritySortingStrategy();
            case "date":
                return new DateSortingStrategy();
            default:
                throw new IllegalArgumentException("Invalid sorting strategy: " + strategy);
        }
    }
}

// Main service class
@Service
public class SortEmailsService {
    public List<ReceiverDTO> sort(List<ReceiverDTO> emails, String strategy) {
        SortStrategyFactory sortStrategyFactory = new SortStrategyFactory();
        SortCommand sortCommand = new SortCommand();
        sortCommand.setCommand(sortStrategyFactory.getStrategy(strategy));
        return sortCommand.execute(emails);
    }
}
