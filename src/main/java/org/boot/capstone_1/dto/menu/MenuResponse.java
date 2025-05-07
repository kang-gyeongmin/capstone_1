package org.boot.capstone_1.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {

    private boolean success;
    private String message;
    private String userId;
    private String userName;
    private List<String> userPlan;
}
