package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.EmployeeDto;
import com.project.kanbanservice.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoFactory {

    public EmployeeDto createEmployeeDto(EmployeeEntity entity) {

        return EmployeeDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
