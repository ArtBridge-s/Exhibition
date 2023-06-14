package com.artbridge.exhibition.application.mapper;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.domain.model.View;
import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.application.dto.ViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link View} and its DTO {@link ViewDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewMapper extends EntityMapper<ViewDTO, View> {
    @Mapping(target = "exhibitionDTO", source = "exhibition", qualifiedByName = "exhibitionId")
    @Mapping(target = "memberDTO", source = "createdMember")
    ViewDTO toDto(View s);

    @Mapping(target = "exhibition", source = "exhibitionDTO")
    @Mapping(target = "createdMember", source = "memberDTO")
    View toEntity(ViewDTO viewDTO);

    @Named("exhibitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExhibitionDTO toDtoExhibitionId(Exhibition exhibition);
}
