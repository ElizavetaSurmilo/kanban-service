package com.project.kanbanservice.repositories;

import com.project.kanbanservice.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long>{
}
