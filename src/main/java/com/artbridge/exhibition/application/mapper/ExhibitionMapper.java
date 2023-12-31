package com.artbridge.exhibition.application.mapper;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Exhibition} and its DTO {@link ExhibitionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExhibitionMapper extends EntityMapper<ExhibitionDTO, Exhibition> {
    @Mapping(target = "createdMemberDTO", source = "createdMember")
    @Mapping(target = "artistDTO", source = "artist")
    @Mapping(target = "periodDTO", source = "period")
    ExhibitionDTO toDto(Exhibition exhibition);

    @Mapping(target = "createdMember", source = "createdMemberDTO")
    @Mapping(target = "artist", source = "artistDTO")
    @Mapping(target = "period", source = "periodDTO")
    Exhibition toEntity(ExhibitionDTO exhibitionDTO);
}
