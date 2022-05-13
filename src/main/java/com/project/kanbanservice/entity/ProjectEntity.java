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
    private DirectorEntity director;

    @OneToMany(mappedBy = "project")
    private Set<TaskEntity> tasks;

    @ManyToMany
    private Set<EmployeeEntity> employees;

    @ManyToMany(mappedBy = "projects")
    Set<TagEntity> tags;

}
