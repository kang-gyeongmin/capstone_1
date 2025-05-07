package org.boot.capstone_1.entity.study;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "toeic_word")
public class ToeicWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toeicWordId;

    private String part;
    private String word;
    private String mean;
}
