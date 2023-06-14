package com.artbridge.exhibition.infrastructure.repository;

import com.artbridge.exhibition.domain.model.Exhibition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Exhibition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExhibitionRepository extends MongoRepository<Exhibition, String> {}
