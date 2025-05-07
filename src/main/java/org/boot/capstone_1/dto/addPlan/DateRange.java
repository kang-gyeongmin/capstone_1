package org.boot.capstone_1.dto.addPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateRange {
    private String title; // 설명
    private LocalDate startDate; // 시작일
    private LocalDate endDate;   // 종료일
}
