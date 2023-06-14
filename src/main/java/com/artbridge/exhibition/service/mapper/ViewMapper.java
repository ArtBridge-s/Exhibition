package com.artbridge.exhibition.service.mapper;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.domain.model.View;
import com.artbridge.exhibition.service.dto.ExhibitionDTO;
import com.artbridge.exhibition.service.dto.ViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link View} and its DTO {@link ViewDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewMapper extends EntityMapper<ViewDTO, View> {
    @Mapping(target = "artwork", source = "artwork", qualifiedByName = "exhibitionId")
    ViewDTO toDto(View s);

    @Named("exhibitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExhibitionDTO toDtoExhibitionId(Exhibition exhibition);
}
