package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.web.response.Exhibition_GET_LIST_STATUS_OK_Res;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface Exhibition_GET_LIST_STATUS_OK_Res_Mapper extends ResReqMapper<ExhibitionDTO, Exhibition_GET_LIST_STATUS_OK_Res> {

    @Mapping(target = "periodDTO", source = "periodDTO")
    @Mapping(target = "artistDTO", source = "artistDTO")
    @Mapping(target = "createdMemberDTO", source = "createdMemberDTO")
    Exhibition_GET_LIST_STATUS_OK_Res toRes(ExhibitionDTO exhibitionDTO);


    @Mapping(target = "periodDTO", source = "periodDTO")
    @Mapping(target = "artistDTO", source = "artistDTO")
    @Mapping(target = "createdMemberDTO", source = "createdMemberDTO")
    ExhibitionDTO toDto(Exhibition_GET_LIST_STATUS_OK_Res exhibition_get_list_status_ok_res);

}
