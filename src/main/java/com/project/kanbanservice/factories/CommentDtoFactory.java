package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.CommentDto;
import com.project.kanbanservice.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoFactory {

    public CommentDto createCommentDto(CommentEntity entity) {

        return CommentDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .username(entity.getUsername())
                .creationDate(entity.getCreationDate())
                .img(entity.getImg())
                .build();
    }
}
