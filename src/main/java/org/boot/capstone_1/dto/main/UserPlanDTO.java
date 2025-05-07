package org.boot.capstone_1.dto.main;

import lombok.Data;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlanDTO {
    private long dDay;
    private double completion;
    private LocalDate startDate;
    private LocalDate endDate;
}