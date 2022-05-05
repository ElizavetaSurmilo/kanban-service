package com.project.kanbanservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @JoinTable(
            name = "directors_tags",
            joinColumns = { @JoinColumn(name = "directors_id") },
            inverseJoinColumns = { @JoinColumn(name = "tags_id") }
    )
    private Set<DirectorEntity> directors;

    @ManyToMany
    @JoinTable(
            name = "employees_tags",
            joinColumns = { @JoinColumn(name = "employees_id") },
            inverseJoinColumns = { @JoinColumn(name = "tags_id") }
    )
    private Set<EmployeeEntity> employees;

    @ManyToMany
    @JoinTable(
            name = "projects_tags",
            joinColumns = { @JoinColumn(name = "projects_id") },
            inverseJoinColumns = { @JoinColumn(name = "tags_id") }
    )
    private Set<ProjectEntity> projects;
}
