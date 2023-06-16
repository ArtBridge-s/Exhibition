package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.web.request.ExhibitionByAdminReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExhibitionByAdminReq_Mapper extends ResReqMapper<ExhibitionDTO, ExhibitionByAdminReq>{

    @Mapping(target = "periodDTO", source = "periodDTO")
    @Mapping(target = "artistDTO", source = "artistDTO")
    ExhibitionDTO toDto(ExhibitionByAdminReq exhibitionByAdminReq);

    @Mapping(target = "artistDTO", source = "artistDTO")
    @Mapping(target = "periodDTO", source = "periodDTO")
    ExhibitionByAdminReq toReq(ExhibitionDTO exhibitionDTO);

}

