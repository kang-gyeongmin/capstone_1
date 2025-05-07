package org.boot.capstone_1.repository.menu;

import org.boot.capstone_1.entity.study.ToeicWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToeicWordRepository extends JpaRepository<ToeicWord, Long> {

    // 각 파트별 랜덤 30개 가져오기 (중복 단어 방지를 위해 넉넉히)
    @Query(value = "SELECT * FROM toeic_word WHERE part = :part ORDER BY RAND() LIMIT 30", nativeQuery = true)
    List<ToeicWord> findRandomWordsByPartExtended(@Param("part") String part);

    // 오답 보기용 의미 - 같은 단어는 제외
    @Query(value = "SELECT mean FROM toeic_word WHERE word != :word ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<String> findRandomWrongMeans(@Param("word") String word);

}
