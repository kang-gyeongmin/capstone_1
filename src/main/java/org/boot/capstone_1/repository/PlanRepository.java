package org.boot.capstone_1.repository;

import org.boot.capstone_1.entity.study.Cssd;
import org.boot.capstone_1.entity.study.Eip;
import org.boot.capstone_1.entity.Plan;
import org.boot.capstone_1.entity.study.Toeic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {


    @Query("SELECT u.studyField FROM Plan u WHERE u.usersId = :usersId")
    List<String> findStudyFieldsByUsersId(@Param("userId") Long usersId);

    // usersId 기준으로 해당 유저의 모든 Plan 리스트 반환
    @Query("SELECT p FROM Plan p WHERE p.usersId.usersId = :usersId")
    List<Plan> findByUsersId(@Param("usersId") Long usersId);

    @Query("SELECT SUM(t.studyTime) FROM Toeic t")
    Integer getToeicTotalStudyTime();

    @Query("SELECT SUM(e.studyTime) FROM Eip e")
    Integer getEipTotalStudyTime();

    @Query("SELECT SUM(c.studyTime) FROM Cssd c")
    Integer getCssdTotalStudyTime();

    List<Toeic> findAllByOrderByToeicIdAsc();

    List<Eip> findAllByOrderByEipIdAsc();

    List<Cssd> findAllByOrderByCssdIdAsc();

    // 단원 찾기 쿼리들
    @Query("SELECT t FROM Toeic t WHERE t.unitNum = :unitNum")
    Toeic findToeicByUnitNum(@Param("unitNum") String unitNum);

    @Query("SELECT c FROM Cssd c WHERE c.unitNum = :unitNum")
    Cssd findCssdByUnitNum(@Param("unitNum") String unitNum);

    @Query("SELECT e FROM Eip e WHERE e.unitNum = :unitNum")
    Eip findEipByUnitNum(@Param("unitNum") String unitNum);

}