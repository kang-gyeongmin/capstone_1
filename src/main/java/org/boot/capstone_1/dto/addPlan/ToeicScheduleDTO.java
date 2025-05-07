package org.boot.capstone_1.dto.addPlan;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToeicScheduleDTO {
    private String examTitle;
    private String examDate;
    private String registrationTitle;
    private String registrationStart;
    private String registrationEnd;

    public ToeicScheduleDTO(String examDate, String registrationStart, String registrationEnd) {
        this.examTitle = "토익 시험일";
        this.examDate = examDate;
        this.registrationTitle = "토익 시험 원서접수 기간";
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
    }
}
