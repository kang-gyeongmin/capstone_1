package org.boot.capstone_1.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.dto.main.UpdateStudyDateRequest;
import org.boot.capstone_1.dto.main.UpdateStudyStatusRequest;
import org.boot.capstone_1.dto.main.CalendarItemDTO;
import org.boot.capstone_1.dto.main.MainResponse;
import org.boot.capstone_1.dto.main.UserPlanDTO;
import org.boot.capstone_1.entity.Plan;
import org.boot.capstone_1.repository.*;
import org.boot.capstone_1.service.CalendarService;
import org.boot.capstone_1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.boot.capstone_1.security.JwtUtil;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PlanRepository planRepository;
    private final UsersToeicRepository usersToeicRepository;
    private final UsersCssdRepository usersCssdRepository;
    private final UsersEipRepository usersEipRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final CalendarService calendarService;

    @GetMapping("/")
    public ResponseEntity<MainResponse> getMainPlan(HttpServletRequest httpRequest) {

        String token = httpRequest.getHeader("Authorization");
        if(!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body(new MainResponse(false, "Invalid token.", null, null, null, null));
        }

        String userId = jwtUtil.extractUserId(token);
        Long usersId = userService.getUsersIdFromUserId(userId);

        List<Plan> plans = planRepository.findByUsersId(usersId);

        Map<String, UserPlanDTO> userPlanMap = new HashMap<>();

        for (Plan plan : plans) {

            UserPlanDTO dto = new UserPlanDTO();

            String studyField= plan.getStudyField();

            if(Objects.equals(studyField, "toeic")) {
                long count = usersToeicRepository.countCompletedToeicByUsersId(usersId);
                double completion = (double) count/38;
                dto.setCompletion(completion*100);
            } else if (Objects.equals(studyField, "eip")) {
                long count = usersEipRepository.countCompletedEipByUsersId(usersId);
                double completion = (double)count/163;
                dto.setCompletion(completion*100);
            } else {
                long count = usersCssdRepository.countCompletedCssdByUsersId(usersId);
                double completion = (double)count/172;
                dto.setCompletion(completion*100);
            }

            dto.setStartDate(plan.getStartDate());
            dto.setEndDate(plan.getEndDate());
            dto.setDDay(ChronoUnit.DAYS.between(LocalDate.now(ZoneId.of("Asia/Seoul")), plan.getEndDate()));

            userPlanMap.put(plan.getStudyField(), dto);
        }

        // 캘린더 데이터 가져오기
        Map<String, List<CalendarItemDTO>> calendarData = calendarService.getCalendarData(usersId);

        MainResponse response = new MainResponse(
                true,
                "Main plan and calendar data loaded successfully.",
                userId,
                userRepository.findByUserId(userId).get().getUserName(),
                userPlanMap,
                calendarData
        );
        return ResponseEntity.ok(response);

    }

    @PostMapping("/calendar/update-date")
    public ResponseEntity<String> updateStudyDate(@RequestBody UpdateStudyDateRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
        }

        // jwt 에서 usersId 추출
        String userId = jwtUtil.extractUserId(token);
        Long usersId = userService.getUsersIdFromUserId(userId);
        request.setUsersId(usersId);

        calendarService.updateStudyDate(request);
        return ResponseEntity.ok("Study date updated");
    }

    @PostMapping("/calendar/update-status")
    public ResponseEntity<String> updateStudyStatus(@RequestBody UpdateStudyStatusRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
        }

        // jwt 에서 usersId 추출
        String userId = jwtUtil.extractUserId(token);
        Long usersId = userService.getUsersIdFromUserId(userId);
        request.setUsersId(usersId);

        calendarService.updateStudyStatus(request);
        return ResponseEntity.ok("Study status updated");
    }
}
