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
    private DirectorEntity director;

    @OneToMany(mappedBy = "employee")
    private Set<TaskEntity> tasks;

    @ManyToMany(mappedBy = "employees")
    Set<ProjectEntity> projects;

    @ManyToMany(mappedBy = "employees")
    Set<TagEntity> tags;

    @OneToOne(mappedBy = "employee")
    private ProfileEntity profile;
}
