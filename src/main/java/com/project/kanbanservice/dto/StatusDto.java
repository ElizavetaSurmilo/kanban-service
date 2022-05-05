package com.project.kanbanservice.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {

    @NonNull
    private Long id;

    @NonNull
    private String title;
}
