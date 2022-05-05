package com.project.kanbanservice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String title;

    private String description;

    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private DirectorEntity directors;

    @OneToMany
    @JoinColumn(name = "tasks_id")
    private Set<TaskEntity> tasks;

    @ManyToMany
    @JoinTable(
            name = "employees_projects",
            joinColumns = { @JoinColumn(name = "employees_id") },
            inverseJoinColumns = { @JoinColumn(name = "projects_id") }
    )
    private Set<EmployeeEntity> employees;

    @ManyToMany
    @JoinTable(
            name = "projects_tags",
            joinColumns = { @JoinColumn(name = "projects_id") },
            inverseJoinColumns = { @JoinColumn(name = "tags_id") }
    )
    Set<ProjectEntity> tags;

}
