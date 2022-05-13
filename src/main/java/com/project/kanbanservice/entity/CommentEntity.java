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
@Table(name = "comments")
public class CommentEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String title;

    private String username;

    private byte[] img;

    private Timestamp creationDate;

    @ManyToOne
    private TaskEntity task;
}
