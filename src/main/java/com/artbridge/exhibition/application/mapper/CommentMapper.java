package com.artbridge.exhibition.application.mapper;

import com.artbridge.exhibition.domain.model.Comment;
import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.application.dto.CommentDTO;
import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
    @Mapping(target = "exhibitionDTO", source = "exhibition", qualifiedByName = "exhibitionId")
    @Mapping(target = "createdMemberDTO", source = "createdMember")
    CommentDTO toDto(Comment s);


    @Mapping(target = "exhibition", source = "exhibitionDTO")
    @Mapping(target = "createdMember", source = "createdMemberDTO")
    Comment toEntity(CommentDTO commentDTO);

    @Named("exhibitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExhibitionDTO toDtoExhibitionId(Exhibition exhibition);
}
