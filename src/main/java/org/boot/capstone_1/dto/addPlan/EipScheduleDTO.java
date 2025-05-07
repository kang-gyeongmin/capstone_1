package org.boot.capstone_1.dto.addPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EipScheduleDTO {

    private String round;

    private DateRange paperApplicationPeriod; // 필기시험 원서접수 기간

    private DateRange additionalPaperApplicationPeriod; // 필기시험 추가 접수 기간

    private DateRange paperExamPeriod; // 필기시험 시행 기간
}
