package org.boot.capstone_1.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamLinkResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
