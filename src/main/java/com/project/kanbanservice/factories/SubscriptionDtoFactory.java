package com.project.kanbanservice.factories;

import com.project.kanbanservice.dto.SubscriptionDto;
import com.project.kanbanservice.entity.SubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionDtoFactory {

    public SubscriptionDto createSubscriptionDto(SubscriptionEntity entity) {

        return SubscriptionDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .teamLimit(entity.getTeamLimit())
                .price(entity.getPrice())
                .build();
    }
}