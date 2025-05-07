package org.boot.capstone_1.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamLinkDTO {
    private String examName; // 시험 이름
    private String officialUrl; // 공식 홈페이지
    private List<PastExamLink> pastExamLinks; // 기출문제 링크 목록 (이름 + 링크)
    private List<YoutubeLink> youtubeLinks;
}
