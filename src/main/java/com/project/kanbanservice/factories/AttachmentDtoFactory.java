package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.AttachmentDto;
import com.project.kanbanservice.entity.AttachmentEntity;
import com.project.kanbanservice.entity.TagEntity;
import org.springframework.stereotype.Component;

@Component
public class AttachmentDtoFactory {

    public AttachmentDto createAttachmentDto(AttachmentEntity entity) {

        return AttachmentDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .document(entity.getDocument())
                .build();
    }
}
