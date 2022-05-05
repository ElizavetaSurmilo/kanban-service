package com.project.kanbanservice.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    private String description;

    @NonNull
    @JsonProperty("creation_date")
    private Timestamp creationDate;
}
