package com.artbridge.exhibition.application.mapper;

import com.artbridge.exhibition.application.dto.PeriodDTO;
import com.artbridge.exhibition.domain.valueobject.Period;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeriodMapper extends EntityMapper<PeriodDTO, Period>{}

