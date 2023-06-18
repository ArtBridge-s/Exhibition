package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.CommentDTO;
import com.artbridge.exhibition.web.response.Comment_GET_Res;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Comment_GET_Res_Mapper extends ResReqMapper<CommentDTO, Comment_GET_Res> {

    @Mapping(target = "createdMemberDTO", source = "createdMemberDTO")
    Comment_GET_Res toReq(CommentDTO commentDTO);

}
