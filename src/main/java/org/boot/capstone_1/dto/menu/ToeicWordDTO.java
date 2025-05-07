package org.boot.capstone_1.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToeicWordDTO {
    private Long toeicWordId;
    private String part;
    private String word;
    private String mean;
}
