// ExamLinkService.java
package org.boot.capstone_1.service;

import org.boot.capstone_1.dto.menu.ExamLinkDTO;
import org.boot.capstone_1.dto.menu.PastExamLink;
import org.boot.capstone_1.dto.menu.YoutubeLink;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamLinkService {

    public List<ExamLinkDTO> getAllExamLinks() {
        return List.of(
                new ExamLinkDTO(
                        "정보처리기사",
                        "https://www.q-net.or.kr/",
                        List.of(
                                new PastExamLink("시나공 기출문제 (~2024년도까지)", "https://www.sinagong.co.kr/pds/001001001/past-exams"),
                                new PastExamLink("CBT 2022년 4월 기출문제", "https://www.comcbt.com/xe/iz/5851061"),
                                new PastExamLink("CBT 2022년 3월 기출문제", "https://www.comcbt.com/xe/iz/5691901"),
                                new PastExamLink("CBT 2021년 8월 기출문제", "https://www.comcbt.com/xe/iz/5290363"),
                                new PastExamLink("CBT 2021년 5월 기출문제", "https://www.comcbt.com/xe/iz/5093289"),
                                new PastExamLink("CBT 2021년 3월 기출문제", "https://www.comcbt.com/xe/iz/4973760"),
                                new PastExamLink("CBT 2020년 9월 기출문제", "https://www.comcbt.com/xe/iz/4566979"),
                                new PastExamLink("CBT 2020년 8월 기출문제", "https://www.comcbt.com/xe/iz/4493129"),
                                new PastExamLink("CBT 2020년 6월 기출문제", "https://www.comcbt.com/xe/iz/4345791")
                        ),
                        List.of(
                                new YoutubeLink("유튜브 시나공 2025","https://www.youtube.com/watch?v=bKaHDEkfPdw&list=PLpYNFXUfkvDonM5Z6EYL9hF_ImFgrgpYz","https://i.ytimg.com/vi/bKaHDEkfPdw/hqdefault.jpg?sqp=-oaymwEnCPYBEIoBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLDM1Mi5i-SsazxD_tQiKVZ38uLdHg"),
                                new YoutubeLink("유튜브 이기적 영진닷컴 절대족보","https://www.youtube.com/watch?v=JhKOsZuMDWs&list=PL6i7rGeEmTvqEjTJF3PJR4a1N9KTPpfw0","https://i.ytimg.com/vi/JhKOsZuMDWs/hqdefault.jpg?sqp=-oaymwEnCPYBEIoBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLA9ON2ppR1OwyVsQCk4_0e9DOvz4g")
                        )
                ),
                new ExamLinkDTO(
                        "TOEIC",
                        "https://exam.toeic.co.kr/",
                        List.of(
                                new PastExamLink("해커스 영어 매일 토익 풀기", "https://www.hackers.co.kr/?c=s_toeic/toeic_study/drc&keywd=haceng_submain_lnb_toeic_drc&logger_kw=haceng_submain_lnb_toeic_drc"),
                                new PastExamLink("누구나-ETS 토익 정기시험 기출문제집 1000 LC", "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788917239492.jpg"),
                                new PastExamLink("누구나-ETS 토익 정기시험 기출문제집 1000 RC", "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788917239508.jpg"),
                                new PastExamLink("고득점 준비-해커스 토익 LC 실전 1000제", "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788965425397.jpg"),
                                new PastExamLink("고득점 준비-해커스 토익 RC 실전 1000제", "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788965425380.jpg")
                        ),
                        List.of(
                                new YoutubeLink("YBM, 박혜원T 추천","https://www.ybmbooks.com/","https://imagesisa.ybmnet.co.kr/platform/exam_ybmnet_co_kr/common/YBMTOEIC_logo_slogan.gif?ver2024"),
                                new YoutubeLink("해커스 실전 1000제 강의, LC: 한승태 / RC: 김동영","https://www.youtube.com/watch?v=JhKOsZuMDWs&list=PL6i7rGeEmTvqEjTJF3PJR4a1N9KTPpfw0","https://gscdn.hackers.co.kr/hackers/images/layout/common/logo_re_new.png"),
                                new YoutubeLink("유튜브 김대균 토익킹", "https://www.youtube.com/@toeicking990/playlists","https://yt3.googleusercontent.com/SLlxbb1O6wiDw-WPWcSyWvaoBKepiSHUViDT-o_xG_Sf6ZLQADmjOIq-G7PRvF2mTMI6SahiPA=s160-c-k-c0x00ffffff-no-rj")
                        )
                ),
                new ExamLinkDTO(
                        "컴퓨터활용능력시험 1급 필기",
                        "https://license.korcham.net/",
                        List.of(
                                new PastExamLink("시나공 기출문제 (~2024년도까지)", "https://www.sinagong.co.kr/pds/003001001/past-exams"),
                                new PastExamLink("CBT 2020년 7월 기출문제", "https://www.comcbt.com/xe/cpt_cert_prev2"),
                                new PastExamLink("CBT 2020년 2월 기출문제", "https://www.comcbt.com/xe/c1/4068471"),
                                new PastExamLink("CBT 2019년 8월 기출문제", "https://www.comcbt.com/xe/c1/3735701"),
                                new PastExamLink("CBT 2019년 3월 기출문제", "https://www.comcbt.com/xe/c1/3434634")
                        ),
                        List.of(
                                new YoutubeLink("유튜브 이기적 영진닷컴", "https://www.youtube.com/watch?v=5EpVbkZLXrE&list=PL6i7rGeEmTvqIQVLwUz18Dfo7pOmXwpoT", "https://i.ytimg.com/vi/5EpVbkZLXrE/hqdefault.jpg?sqp=-oaymwEnCPYBEIoBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLCp-W9ShUv4d78dFlAyJ9S959Npog"),
                                new YoutubeLink("유튜브 균쌤", "https://www.youtube.com/watch?v=aXvbO-j8sT0&list=PL7qs4zo3uSILj4uDOx326k-mpPuPKPOhK", "https://i.ytimg.com/vi/aXvbO-j8sT0/hqdefault.jpg?sqp=-oaymwEnCPYBEIoBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLBcdglKZD-MkMdJt1QY3YgSwwy-5A"),
                                new YoutubeLink("유튜브 박쌤컴퓨터", "https://www.youtube.com/watch?v=sT1fKa6aBLE&list=PLuReFOEvNNTS0PTBUDhIZsmQjJngPJqgC", "https://i.ytimg.com/vi/ubGyZTUQpvo/hqdefault.jpg?sqp=-oaymwEnCPYBEIoBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLC_gKqqxb52fSSDmEvSkXq1ifBI8A")
                        )
                )
        );
    }
}
