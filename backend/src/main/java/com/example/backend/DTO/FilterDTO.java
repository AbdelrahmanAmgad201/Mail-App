package com.example.backend.DTO;
import com.example.backend.DTO.ReceiverDTO.UserDTO;
import com.example.backend.Entities.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private long userId;
    private String startDate;
    private String endDate;
    private List<String> receivers;
    private List<String> senders;
    private List<String> subjects;
    private String priority;
    private String body;
    private String attachment;
}
