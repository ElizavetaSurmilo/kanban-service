package com.project.kanbanservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String title;

    private int teamLimit;

    private double price;

    @OneToMany
    @JoinColumn(name = "directors_id")
    private Set<DirectorEntity> directors;
}
