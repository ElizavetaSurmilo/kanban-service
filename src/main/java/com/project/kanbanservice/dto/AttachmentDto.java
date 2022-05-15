package com.project.kanbanservice.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private byte[] document;
}
