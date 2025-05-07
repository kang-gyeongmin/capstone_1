package org.boot.capstone_1.dto.menu;

import lombok.Data;

import java.util.List;

@Data
public class ToeicWordTestQuestionDTO {

    private String word;           // 문제로 보여줄 단어
    private String correctMean;    // 정답
    private List<String> options;  // 보기 (정답 + 오답들, 오답은 랜덤)

}