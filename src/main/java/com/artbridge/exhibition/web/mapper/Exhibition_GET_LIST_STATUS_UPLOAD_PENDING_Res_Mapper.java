package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.web.response.Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res_Mapper extends ResReqMapper<ExhibitionDTO, Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res>{}

