package com.artbridge.exhibition.repository;

import com.artbridge.exhibition.domain.View;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the View entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewRepository extends MongoRepository<View, String> {}
