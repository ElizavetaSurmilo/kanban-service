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
@Table(name = "directors")
public class DirectorEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private int countEmployees;

    private String connectionId;

    @OneToMany
    @JoinColumn(name = "employees_id")
    private Set<EmployeeEntity> employees;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private SubscriptionEntity subscription;

    @OneToMany
    @JoinColumn(name = "projects_id")
    private Set<ProjectEntity> projects;

    @OneToMany
    @JoinColumn(name = "tasks_id")
    private Set<TaskEntity> task;

    @ManyToMany
    @JoinTable(
            name = "directors_tags",
            joinColumns = { @JoinColumn(name = "directors_id") },
            inverseJoinColumns = { @JoinColumn(name = "tags_id") }
    )
    Set<ProjectEntity> tags;
}
