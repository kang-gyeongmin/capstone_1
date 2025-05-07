package org.boot.capstone_1.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.dto.menu.ToeicWordTestQuestionDTO;
import org.boot.capstone_1.dto.menu.ExamLinkDTO;
import org.boot.capstone_1.dto.menu.ExamLinkResponse;
import org.boot.capstone_1.dto.menu.MenuResponse;
import org.boot.capstone_1.dto.menu.CssdMainTopicDTO;
import org.boot.capstone_1.dto.menu.EipMainTopicDTO;
import org.boot.capstone_1.dto.menu.ToeicWordDTO;
import org.boot.capstone_1.dto.main.MainResponse;
import org.boot.capstone_1.repository.UserRepository;
import org.boot.capstone_1.security.JwtUtil;
import org.boot.capstone_1.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PlanService planService;
    private final SummaryService summaryService;
    private final ToeicWordService toeicWordService;
    private ExamLinkService examLinkService;
    private final JwtUtil jwtUtil;

    @GetMapping("")
    public ResponseEntity<MenuResponse> getMainMenu(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body(null);
        }

        String userId = jwtUtil.extractUserId(token);
        Long usersId = userService.getUsersIdFromUserId(userId);

        MenuResponse response = new MenuResponse();
        response.setUserName(userRepository.findByUserId(userId).get().getUserName());

        List<String> userPlan = planService.getStudyFieldsByUsersId(usersId);

        response.setUserId(userId);
        response.setSuccess(true);
        response.setMessage("Successfully loaded menu information.");
        response.setUserPlan(userPlan);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/learning-summary/cssd")
    public ResponseEntity<?> getCssdMainTopics(HttpServletRequest httpRequest) {

        String token = httpRequest.getHeader("Authorization");
        if(!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body(new MainResponse(false, "Invalid token.", null, null, null, null));
        }

        List<CssdMainTopicDTO> summaries = summaryService.getAllCssdTopics();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Successfully retrieved CSSD main summary list.");
        response.put("summary", summaries);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/learning-summary/eip")
    public ResponseEntity<?> getEipMainTopics(HttpServletRequest httpRequest) {

        String token = httpRequest.getHeader("Authorization");
        if(!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body(new MainResponse(false, "Invalid token.", null, null, null, null));
        }

        List<EipMainTopicDTO> summaries = summaryService.getAllEipTopics();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Successfully retrieved Eip main summary list.");
        response.put("summary", summaries);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/toeic-words")
    public ResponseEntity<?> getToeicWords(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        if(!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body(new MainResponse(false, "Invalid token.", null, null, null, null));
        }

        List<ToeicWordDTO> words = toeicWordService.getAllToeicWords();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Successfully retrieved English word list.");
        response.put("words", words);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/toeic-test/start")
    public ResponseEntity<List<ToeicWordTestQuestionDTO>> startTest(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        if(!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<ToeicWordTestQuestionDTO> questions = toeicWordService.generateToeicTest();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/links")
    public ResponseEntity<ExamLinkResponse<List<ExamLinkDTO>>> getExamLinks(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        if(!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body(new ExamLinkResponse(false, "Invalid token.", Collections.emptyList()));
        }

        List<ExamLinkDTO> links = examLinkService.getAllExamLinks();
        ExamLinkResponse<List<ExamLinkDTO>> response = new ExamLinkResponse<>(
                true,
                "Exam links loaded successfully",
                links
        );
        return ResponseEntity.ok(response);
    }
}
