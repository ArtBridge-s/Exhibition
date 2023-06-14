package com.artbridge.exhibition.service.mapper;

import com.artbridge.exhibition.domain.Comment;
import com.artbridge.exhibition.domain.Exhibition;
import com.artbridge.exhibition.service.dto.CommentDTO;
import com.artbridge.exhibition.service.dto.ExhibitionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
    @Mapping(target = "artwork", source = "artwork", qualifiedByName = "exhibitionId")
    CommentDTO toDto(Comment s);

    @Named("exhibitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExhibitionDTO toDtoExhibitionId(Exhibition exhibition);
}
