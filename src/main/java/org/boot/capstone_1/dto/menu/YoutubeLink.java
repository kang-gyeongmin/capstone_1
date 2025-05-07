package org.boot.capstone_1.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeLink {
    private String title; // 예: "채널명"
    private String url;
    private String imageUrl;
}
