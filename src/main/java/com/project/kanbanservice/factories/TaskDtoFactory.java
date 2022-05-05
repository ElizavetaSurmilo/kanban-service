package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.TaskDto;
import com.project.kanbanservice.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {

    public TaskDto createTaskDto(TaskEntity entity) {

        return TaskDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .deadline(entity.getDeadline())
                .build();
    }
}
