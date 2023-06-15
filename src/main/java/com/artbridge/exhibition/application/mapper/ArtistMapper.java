package com.artbridge.exhibition.application.mapper;

import com.artbridge.exhibition.application.dto.ArtistDTO;
import com.artbridge.exhibition.domain.valueobject.Artist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist>{}

