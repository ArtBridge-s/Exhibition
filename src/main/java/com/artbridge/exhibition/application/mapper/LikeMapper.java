package com.artbridge.exhibition.application.mapper;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.domain.model.Like;
import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.application.dto.LikeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Like} and its DTO {@link LikeDTO}.
 */
@Mapper(componentModel = "spring")
public interface LikeMapper extends EntityMapper<LikeDTO, Like> {
    @Mapping(target = "exhibitionDTO", source = "exhibition", qualifiedByName = "exhibitionId")
    @Mapping(target = "memberDTO", source = "createdMember")
    LikeDTO toDto(Like s);

    @Mapping(target = "exhibition", source = "exhibitionDTO")
    @Mapping(target = "createdMember", source = "memberDTO")
    Like toEntity(LikeDTO dto);

    @Named("exhibitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExhibitionDTO toDtoExhibitionId(Exhibition exhibition);
}
