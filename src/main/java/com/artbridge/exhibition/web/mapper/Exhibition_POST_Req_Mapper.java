package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.web.request.Exhibition_POST_Req;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Exhibition_POST_Req_Mapper extends ResReqMapper<ExhibitionDTO, Exhibition_POST_Req> {

    @Mapping(target = "periodDTO", source = "periodDTO")
    @Mapping(target = "artistDTO", source = "artistDTO")
    ExhibitionDTO toDto(Exhibition_POST_Req exhibition_post_req);

    @Mapping(target = "artistDTO", source = "artistDTO")
    @Mapping(target = "periodDTO", source = "periodDTO")
    Exhibition_POST_Req toReq(ExhibitionDTO exhibitionDTO);


}
