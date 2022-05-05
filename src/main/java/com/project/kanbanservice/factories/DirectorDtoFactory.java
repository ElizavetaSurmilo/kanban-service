package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.DirectorDto;
import com.project.kanbanservice.entity.DirectorEntity;
import org.springframework.stereotype.Component;

@Component
public class DirectorDtoFactory {

    public DirectorDto createDirectorDto(DirectorEntity entity) {

        return DirectorDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .countEmployees(entity.getCountEmployees())
                .connectionId(entity.getConnectionId())
                .build();
    }
}
