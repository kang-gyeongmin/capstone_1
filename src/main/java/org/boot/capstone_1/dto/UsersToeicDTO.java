package org.boot.capstone_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersToeicDTO {

    private Integer usersId;
    private Integer toeicId;
    private LocalDate studyDate;
    private Boolean studyStatus;
    private String unitNum;
    private String unitName;
    private Integer unitImportance;
    private String sectionSummary;

}
