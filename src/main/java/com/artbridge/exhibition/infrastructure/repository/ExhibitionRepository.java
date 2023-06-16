package com.artbridge.exhibition.infrastructure.repository;

import com.artbridge.exhibition.domain.enumeration.Status;
import com.artbridge.exhibition.domain.model.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Exhibition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExhibitionRepository extends MongoRepository<Exhibition, String> {

    Optional<Exhibition> findByIdAndStatus(String id, Status status);

    Page<Exhibition> findAllByStatus(Status status, org.springframework.data.domain.Pageable pageable);
}
