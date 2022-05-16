package com.project.kanbanservice.repositories;

import com.project.kanbanservice.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {

    Optional<DirectorEntity> findByUsername (String username);

    Optional<DirectorEntity> findByEmail (String email);

    Optional<DirectorEntity> findByConnectionId (String connectionId);

    Stream<DirectorEntity> streamAllBy();

    Stream<DirectorEntity> streamAllByUsername(String username);

    Stream<DirectorEntity> streamAllByUsernameContainingIgnoreCase(String username);
}
