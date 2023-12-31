package com.artbridge.exhibition.application.service;

import com.artbridge.exhibition.domain.model.Comment;
import com.artbridge.exhibition.application.dto.CommentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Comment}.
 */
public interface CommentService {

    CommentDTO saveCommentForExhibition(String exhibitionId, CommentDTO commentDTO);

    Page<CommentDTO> findAllByExhibitionId(String exhibitionId, Pageable pageable);

    /**
     * Updates a comment.
     *
     * @param commentDTO the entity to update.
     * @return the persisted entity.
     */
    CommentDTO update(CommentDTO commentDTO);

    /**
     * Partially updates a comment.
     *
     * @param commentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommentDTO> partialUpdate(CommentDTO commentDTO);

    /**
     * Get all the comments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommentDTO> findOne(String id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

}
