package com.artbridge.exhibition.infrastructure.repository;

import com.artbridge.exhibition.domain.model.View;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the View entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewRepository extends MongoRepository<View, String> {}
