package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.CommentDTO;
import com.artbridge.exhibition.web.request.Comment_POST_Req;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Comment_POST_Req_Mapper extends ResReqMapper<CommentDTO, Comment_POST_Req> {

}
