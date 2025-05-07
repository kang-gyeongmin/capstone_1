package org.boot.capstone_1.dto.main;

import lombok.Data;

@Data
public class UpdateStudyStatusRequest {

    private Long usersId;       // jwt 에서 추출해서 서비스로 넘겨줄 때 쓰임
    private String studyField;  // 과목: "toeic", "cssd", "eip"
    private String unitNum;

}