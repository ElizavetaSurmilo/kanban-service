package com.project.kanbanservice.repositories;

import com.project.kanbanservice.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
