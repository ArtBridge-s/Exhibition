package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.web.request.ExhibitionRevisionRequest_Req;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExhibitionRevisionRequest_Req_Mapper extends ResReqMapper<ExhibitionDTO, ExhibitionRevisionRequest_Req>{}

