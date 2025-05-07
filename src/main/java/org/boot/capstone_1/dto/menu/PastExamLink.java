package org.boot.capstone_1.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PastExamLink {
    private String title; // 예: "2023년 1회 기출"
    private String url;   // 예: "https://example.com/past-exam-2023-1"
}
