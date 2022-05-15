package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.ProfileDto;
import com.project.kanbanservice.entity.ProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileDtoFactory {

    public ProfileDto createProfileDto(ProfileEntity entity) {

        return ProfileDto.builder()
                .id(entity.getId())
                .full_name(entity.getFull_name())
                .phone(entity.getPhone())
                .about(entity.getAbout())
                .img(entity.getImg())
                .build();
    }
}
