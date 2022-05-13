package com.project.kanbanservice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String title;

    private String description;

    private Timestamp creationDate;

    private Date deadline;

    @ManyToOne
    private DirectorEntity director;

    @ManyToOne
    private ProjectEntity project;

    @ManyToOne
    private StatusEntity status;

    @ManyToOne
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "task")
    private Set<CommentEntity> comments;
}
