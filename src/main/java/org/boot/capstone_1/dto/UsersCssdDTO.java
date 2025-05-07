package org.boot.capstone_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersCssdDTO {

    private Integer usersId;
    private Integer cssdId;
    private LocalDate studyDate;
    private Boolean studyStatus;
    private String unitNum;
    private String unitName;
    private Integer unitImportance;

}