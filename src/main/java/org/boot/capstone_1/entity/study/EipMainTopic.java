package org.boot.capstone_1.entity.study;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "eip_main_topic")
public class EipMainTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String eipMainTopicId;

    private String summary;
}
