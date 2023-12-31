package com.artbridge.exhibition.infrastructure.repository;

import com.artbridge.exhibition.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Page<Comment> findAllByExhibition_Id(String exhibitionId, org.springframework.data.domain.Pageable pageable);
}
