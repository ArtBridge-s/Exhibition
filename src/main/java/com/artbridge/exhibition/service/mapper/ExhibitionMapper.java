package com.artbridge.exhibition.service.mapper;

import com.artbridge.exhibition.domain.Exhibition;
import com.artbridge.exhibition.service.dto.ExhibitionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Exhibition} and its DTO {@link ExhibitionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExhibitionMapper extends EntityMapper<ExhibitionDTO, Exhibition> {}
