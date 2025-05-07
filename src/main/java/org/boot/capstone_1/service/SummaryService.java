package org.boot.capstone_1.service;

import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.dto.menu.CssdMainTopicDTO;
import org.boot.capstone_1.dto.menu.EipMainTopicDTO;
import org.boot.capstone_1.repository.menu.CssdMainTopicRepository;
import org.boot.capstone_1.repository.menu.EipMainTopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final CssdMainTopicRepository cssdMainTopicRepository;
    private final EipMainTopicRepository eipMainTopicRepository;

    public List<CssdMainTopicDTO> getAllCssdTopics() {
        return cssdMainTopicRepository.findAll().stream()
                .map(topic -> new CssdMainTopicDTO(topic.getCssdMainTopicId(), topic.getSummary()))
                .collect(Collectors.toList());
    }

    public List<EipMainTopicDTO> getAllEipTopics() {
        return eipMainTopicRepository.findAll().stream()
                .map(topic -> new EipMainTopicDTO(topic.getEipMainTopicId(), topic.getSummary()))
                .collect(Collectors.toList());
    }
}
