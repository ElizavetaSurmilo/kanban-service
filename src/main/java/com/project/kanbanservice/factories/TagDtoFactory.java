package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.TagDto;
import com.project.kanbanservice.entity.TagEntity;
import org.springframework.stereotype.Component;

@Component
public class TagDtoFactory {

    public TagDto createTagDto(TagEntity entity) {

        return TagDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
    }
}
