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
@Table(name = "attachments")
public class AttachmentEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String title;

    private byte[] document;

    @ManyToOne
    private TaskEntity task;
}
