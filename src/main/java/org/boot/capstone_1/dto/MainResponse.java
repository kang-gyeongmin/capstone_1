package org.boot.capstone_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainResponse {

    private boolean success;
    private String message;
    private UserDTO user;

}