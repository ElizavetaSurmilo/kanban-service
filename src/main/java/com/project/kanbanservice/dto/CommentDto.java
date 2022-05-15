package com.project.kanbanservice.dto;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    @NonNull
    private Long id;

    @NonNull
    private String text;

    @NonNull
    private String username;

    private byte[] img;

    private Timestamp creationDate;
}
