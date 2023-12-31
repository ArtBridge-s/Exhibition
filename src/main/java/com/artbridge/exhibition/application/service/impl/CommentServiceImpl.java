package com.artbridge.exhibition.application.service.impl;

import com.artbridge.exhibition.domain.model.Comment;
import com.artbridge.exhibition.domain.service.CommentDomainService;
import com.artbridge.exhibition.infrastructure.repository.CommentRepository;
import com.artbridge.exhibition.application.service.CommentService;
import com.artbridge.exhibition.application.dto.CommentDTO;
import com.artbridge.exhibition.application.mapper.CommentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Comment}.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CommentDomainService commentDomainService;


    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, CommentDomainService commentDomainService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.commentDomainService = commentDomainService;
    }

    @Override
    public CommentDTO saveCommentForExhibition(String exhibitionId, CommentDTO commentDTO) {
        log.debug("Request to save Comment : {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);

        comment.setExhibitionId(exhibitionId);
        comment = commentDomainService.initCreatedMember(comment);
        comment = commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Override
    public Page<CommentDTO> findAllByExhibitionId(String exhibitionId, Pageable pageable) {
        log.debug("Request to get all Comments by exhibitionId : {}", exhibitionId);
        return commentRepository.findAllByExhibition_Id(exhibitionId, pageable).map(commentMapper::toDto);
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        log.debug("Request to update Comment : {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public Optional<CommentDTO> partialUpdate(CommentDTO commentDTO) {
        log.debug("Request to partially update Comment : {}", commentDTO);

        return commentRepository
            .findById(commentDTO.getId())
            .map(existingComment -> {
                commentMapper.partialUpdate(existingComment, commentDTO);

                return existingComment;
            })
            .map(commentRepository::save)
            .map(commentMapper::toDto);
    }

    @Override
    public Page<CommentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comments");
        return commentRepository.findAll(pageable).map(commentMapper::toDto);
    }

    @Override
    public Optional<CommentDTO> findOne(String id) {
        log.debug("Request to get Comment : {}", id);
        return commentRepository.findById(id).map(commentMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Comment : {}", id);
        commentRepository.deleteById(id);
    }
}
