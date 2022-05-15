package com.project.kanbanservice.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    @NonNull
    private Long id;

    @NonNull
    private String full_name;

    private String phone;

    private String about;

    private byte[] img;

}
