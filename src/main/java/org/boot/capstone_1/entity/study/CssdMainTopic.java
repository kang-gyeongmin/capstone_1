package org.boot.capstone_1.entity.study;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cssd_main_topic")
public class CssdMainTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cssdMainTopicId;

    private String summary;
}
