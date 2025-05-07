package org.boot.capstone_1.service;

import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.dto.menu.ToeicWordTestQuestionDTO;
import org.boot.capstone_1.dto.menu.ToeicWordDTO;
import org.boot.capstone_1.entity.study.ToeicWord;
import org.boot.capstone_1.repository.menu.ToeicWordRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToeicWordService {

    private final ToeicWordRepository toeicWordRepository;

    public List<ToeicWordDTO> getAllToeicWords() {
        return toeicWordRepository.findAll().stream()
                .map(words -> new ToeicWordDTO(words.getToeicWordId(), words.getPart(), words.getWord(), words.getMean()))
                .collect(Collectors.toList());
    }

    public List<ToeicWordTestQuestionDTO> generateToeicTest() {
        List<ToeicWordTestQuestionDTO> testQuestions = new ArrayList<>();
        Set<String> usedWords = new HashSet<>();

        List<String> parts = List.of("LC", "RC", "VOCA");

        for (String part : parts) {
            List<ToeicWord> candidates = toeicWordRepository.findRandomWordsByPartExtended(part); // 더 많이 불러오기
            int count = 0;

            for (ToeicWord word : candidates) {
                if (usedWords.contains(word.getWord())) continue; // 중복 단어 건너뜀

                usedWords.add(word.getWord());

                String correctMean = word.getMean();
                String wordText = word.getWord();

                // 오답 의미는 겹치는 단어의 의미를 제외하고 랜덤 3개
                List<String> wrongMeans = toeicWordRepository.findRandomWrongMeans(wordText);
                if (wrongMeans.size() < 3) continue;

                List<String> options = new ArrayList<>(wrongMeans);
                options.add(correctMean);     // 정답 추가
                Collections.shuffle(options); // 보기 순서 섞기

                ToeicWordTestQuestionDTO question = new ToeicWordTestQuestionDTO();
                question.setWord(wordText);
                question.setCorrectMean(correctMean);
                question.setOptions(options);

                testQuestions.add(question);
                count++;

                if (count == 5) break; // 이 파트에서 5개 만들었으면 다음 파트로
            }
        }

        return testQuestions;
    }
}
