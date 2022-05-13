package com.project.kanbanservice.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String full_name;

    @Column(unique = true)
    private String phone;

    private String about;

    private byte[] img;

    @OneToOne
    private DirectorEntity director;

    @OneToOne
    private EmployeeEntity employee;}
