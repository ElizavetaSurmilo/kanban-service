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

    @Column(unique = true)
    private String connectionId;

    @OneToMany(mappedBy = "director")
    private Set<EmployeeEntity> employees;

    @ManyToOne
    private SubscriptionEntity subscription;

    @OneToMany(mappedBy = "director")
    private Set<ProjectEntity> projects;

    @OneToMany(mappedBy = "director")
    private Set<TaskEntity> tasks;

    @ManyToMany(mappedBy = "directors")
    Set<TagEntity> tags;

    @OneToOne(mappedBy = "director")
    private ProfileEntity profile;
}
