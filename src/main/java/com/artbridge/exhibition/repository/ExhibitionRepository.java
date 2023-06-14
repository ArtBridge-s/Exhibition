package com.artbridge.exhibition.repository;

import com.artbridge.exhibition.domain.Exhibition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Exhibition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExhibitionRepository extends MongoRepository<Exhibition, String> {}
