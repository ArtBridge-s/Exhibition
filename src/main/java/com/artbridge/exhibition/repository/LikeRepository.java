package com.artbridge.exhibition.repository;

import com.artbridge.exhibition.domain.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Like entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LikeRepository extends MongoRepository<Like, String> {}
