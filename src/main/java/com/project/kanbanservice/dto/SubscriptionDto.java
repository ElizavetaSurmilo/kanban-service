package com.project.kanbanservice.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @JsonProperty("team_limit")
    private int teamLimit;

    @NonNull
    private double price;
}
