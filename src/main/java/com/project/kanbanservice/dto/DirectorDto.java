package com.project.kanbanservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {

    @NonNull
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    @JsonProperty("count_employees")
    private int countEmployees;

    @NonNull
    @JsonProperty("connection_id")
    private String connectionId;
}
