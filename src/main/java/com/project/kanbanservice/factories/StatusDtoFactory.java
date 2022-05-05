package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.StatusDto;
import com.project.kanbanservice.entity.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusDtoFactory {

    public StatusDto createStatusDto(StatusEntity entity) {

        return StatusDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
    }
}
