package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.ProjectDto;
import com.project.kanbanservice.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoFactory {

    public ProjectDto createProjectDto(ProjectEntity entity) {

        return ProjectDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .build();
    }
}
