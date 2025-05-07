package org.boot.capstone_1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.dto.main.UpdateStudyDateRequest;
import org.boot.capstone_1.dto.main.UpdateStudyStatusRequest;
import org.boot.capstone_1.dto.main.CalendarItemDTO;
import org.boot.capstone_1.entity.*;
import org.boot.capstone_1.entity.study.Cssd;
import org.boot.capstone_1.entity.study.Eip;
import org.boot.capstone_1.entity.study.Toeic;
import org.boot.capstone_1.repository.PlanRepository;
import org.boot.capstone_1.repository.UsersCssdRepository;
import org.boot.capstone_1.repository.UsersEipRepository;
import org.boot.capstone_1.repository.UsersToeicRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final PlanRepository planRepository;
    private final UsersToeicRepository usersToeicRepository;
    private final UsersCssdRepository usersCssdRepository;
    private final UsersEipRepository usersEipRepository;

    // 캘린더에 표시할 학습 정보를 생성
    public Map<String, List<CalendarItemDTO>> getCalendarData(Long usersId) {
        Map<String, List<CalendarItemDTO>> calendarMap = new HashMap<>();

        // plan 테이블에서 해당 사용자의 모든 학습 계획을 가져옴
        List<Plan> plans = planRepository.findByUsersId(usersId);

        for (Plan plan : plans) {
            // plan 안에 어떤 과목인지(studyField)를 보고 분기 처리함
            String field = plan.getStudyField();

            if (field.equals("toeic")) {
                // 학습 내역(studies)과 전체 단원 정보(allUnits)를 가져옴
                List<UsersToeic> studies = usersToeicRepository.findByUsersId(usersId);
                List<Toeic> allUnits = planRepository.findAllByOrderByToeicIdAsc();

                for (UsersToeic study : studies) {
                    Toeic unit = allUnits.stream()
                            .filter(t -> t.getToeicId().equals(study.getToeicId()))
                            .findFirst()
                            .orElse(null);
                    if (unit == null) continue;

                    CalendarItemDTO item = new CalendarItemDTO(
                            "toeic",
                            unit.getUnitNum(),
                            unit.getUnitName(),
                            study.isStudyStatus(),
                            unit.getUnitImportance()
                    );
                    String dateStr = study.getStudyDate().toString();
                    calendarMap.computeIfAbsent(dateStr, k -> new ArrayList<>()).add(item);
                }

            } else if (field.equals("cssd")) {
                List<UsersCssd> studies = usersCssdRepository.findByUsersId(usersId);
                List<Cssd> allUnits = planRepository.findAllByOrderByCssdIdAsc();

                for (UsersCssd study : studies) {
                    Cssd unit = allUnits.stream()
                            .filter(c -> c.getCssdId().equals(study.getCssdId()))
                            .findFirst()
                            .orElse(null);
                    if (unit == null) continue;

                    CalendarItemDTO item = new CalendarItemDTO(
                            "cssd",
                            unit.getUnitNum(),
                            unit.getUnitName(),
                            study.isStudyStatus(),
                            unit.getUnitImportance()
                    );
                    String dateStr = study.getStudyDate().toString();
                    calendarMap.computeIfAbsent(dateStr, k -> new ArrayList<>()).add(item);
                }

            } else if (field.equals("eip")) {
                List<UsersEip> studies = usersEipRepository.findByUsersId(usersId);
                List<Eip> allUnits = planRepository.findAllByOrderByEipIdAsc();

                for (UsersEip study : studies) {
                    Eip unit = allUnits.stream()
                            .filter(e -> e.getEipId().equals(study.getEipId()))
                            .findFirst()
                            .orElse(null);
                    if (unit == null) continue;

                    CalendarItemDTO item = new CalendarItemDTO(
                            "eip",
                            unit.getUnitNum(),
                            unit.getUnitName(),
                            study.isStudyStatus(),
                            unit.getUnitImportance()
                    );
                    String dateStr = study.getStudyDate().toString();
                    calendarMap.computeIfAbsent(dateStr, k -> new ArrayList<>()).add(item);
                }
            }
        }

        return calendarMap;
    }

    @Transactional
    public void updateStudyDate(UpdateStudyDateRequest request) {
        Long usersId = request.getUsersId();
        String field = request.getStudyField();
        String unitNum = request.getUnitNum();
        LocalDate newDate = request.getNewStudyDate();

        if (field.equals("toeic")) {
            Toeic toeic = planRepository.findToeicByUnitNum(unitNum);
            if (toeic != null) {
                UsersToeic userStudy = usersToeicRepository.findByUsersIdAndToeicId(usersId, toeic.getToeicId());
                if (userStudy != null) {
                    userStudy.setStudyDate(newDate);
                    usersToeicRepository.save(userStudy);
                }
            }

        } else if (field.equals("cssd")) {
            Cssd cssd = planRepository.findCssdByUnitNum(unitNum);
            if (cssd != null) {
                UsersCssd userStudy = usersCssdRepository.findByUsersIdAndCssdId(usersId, cssd.getCssdId());
                if (userStudy != null) {
                    userStudy.setStudyDate(newDate);
                    usersCssdRepository.save(userStudy);
                }
            }

        } else if (field.equals("eip")) {
            Eip eip = planRepository.findEipByUnitNum(unitNum);
            if (eip != null) {
                UsersEip userStudy = usersEipRepository.findByUsersIdAndEipId(usersId, eip.getEipId());
                if (userStudy != null) {
                    userStudy.setStudyDate(newDate);
                    usersEipRepository.save(userStudy);
                }
            }
        }
    }


    @Transactional
    public void updateStudyStatus(UpdateStudyStatusRequest request) {
        Long usersId = request.getUsersId();
        String field = request.getStudyField();
        String unitNum = request.getUnitNum();

        if (field.equals("toeic")) {
            Toeic toeic = planRepository.findToeicByUnitNum(unitNum);
            if (toeic != null) {
                UsersToeic userStudy = usersToeicRepository.findByUsersIdAndToeicId(usersId, toeic.getToeicId());
                if (userStudy != null) {
                    boolean currentStatus = userStudy.isStudyStatus();
                    userStudy.setStudyStatus(!currentStatus);
                    usersToeicRepository.save(userStudy);
                }
            }

        } else if (field.equals("cssd")) {
            Cssd cssd = planRepository.findCssdByUnitNum(unitNum);
            if (cssd != null) {
                UsersCssd userStudy = usersCssdRepository.findByUsersIdAndCssdId(usersId, cssd.getCssdId());
                if (userStudy != null) {
                    boolean currentStatus = userStudy.isStudyStatus();
                    userStudy.setStudyStatus(!currentStatus);
                    usersCssdRepository.save(userStudy);
                }
            }

        } else if (field.equals("eip")) {
            Eip eip = planRepository.findEipByUnitNum(unitNum);
            if (eip != null) {
                UsersEip userStudy = usersEipRepository.findByUsersIdAndEipId(usersId, eip.getEipId());
                if (userStudy != null) {
                    boolean currentStatus = userStudy.isStudyStatus();
                    userStudy.setStudyStatus(!currentStatus);
                    usersEipRepository.save(userStudy);
                }
            }
        }
    }
}