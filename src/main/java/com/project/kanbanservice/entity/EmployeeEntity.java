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
@Table(name = "employees")
public class EmployeeEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

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
    Set<ProjectEntity> projects;

    @ManyToMany
    @JoinTable(
            name = "employees_tags",
            joinColumns = { @JoinColumn(name = "employees_id") },
            inverseJoinColumns = { @JoinColumn(name = "tags_id") }
    )
    Set<ProjectEntity> tags;
}
