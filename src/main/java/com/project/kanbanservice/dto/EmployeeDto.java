package com.project.kanbanservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    @NonNull
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String password;
}
