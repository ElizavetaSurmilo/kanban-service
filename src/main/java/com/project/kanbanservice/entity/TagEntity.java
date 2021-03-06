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
@Table(name = "tags")
public class TagEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String title;

    @ManyToMany
    private Set<DirectorEntity> directors;

    @ManyToMany
    private Set<EmployeeEntity> employees;

    @ManyToMany
    private Set<ProjectEntity> projects;
}
