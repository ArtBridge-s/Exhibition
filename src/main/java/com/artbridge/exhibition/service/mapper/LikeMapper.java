package com.artbridge.exhibition.service.mapper;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.domain.model.Like;
import com.artbridge.exhibition.service.dto.ExhibitionDTO;
import com.artbridge.exhibition.service.dto.LikeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Like} and its DTO {@link LikeDTO}.
 */
@Mapper(componentModel = "spring")
public interface LikeMapper extends EntityMapper<LikeDTO, Like> {
    @Mapping(target = "artwork", source = "artwork", qualifiedByName = "exhibitionId")
    LikeDTO toDto(Like s);

    @Named("exhibitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExhibitionDTO toDtoExhibitionId(Exhibition exhibition);
}
