package org.boot.capstone_1.dto.main;

import lombok.AllArgsConstructor;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainResponse {
    private boolean success;
    private String message;
    private String userId;
    private String userName;
    private Map<String, UserPlanDTO> userPlan; // toeic, eip, cssd
    private Map<String, List<CalendarItemDTO>> calendar; // 날짜별 학습 일정
}