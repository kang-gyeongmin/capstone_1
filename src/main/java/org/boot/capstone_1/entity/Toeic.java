package org.boot.capstone_1.entity;

import jakarta.persistence.*;

@Entity
@Table(name="toeic")
public class Toeic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toeic_id")
    private Integer toeicId;

    @Column(name = "unit_num")
    private String unitNum;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_importance")
    private int unitImportance;

    @Column(name = "study_time")
    private int studyTime;

    @Column(name = "section_summary")
    private String sectionSummary;
}
