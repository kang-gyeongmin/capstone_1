package org.boot.capstone_1.dto.main;

import lombok.Data;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarItemDTO {
    private String field;      // toeic, eip, cssd
    private String unit;       // 예: 1-1-1
    private String title;      // 소단원 이름
    private boolean isDone;    // 학습 완료 여부
    private int importance;    // 중요도
}