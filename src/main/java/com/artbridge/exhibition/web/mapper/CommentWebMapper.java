package com.artbridge.exhibition.web.mapper;

import com.artbridge.exhibition.application.dto.CommentDTO;
import com.artbridge.exhibition.web.request.CommentRequest;
import com.artbridge.exhibition.web.response.CommentGetResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentWebMapper {

    CommentRequest mapToCommentRequest(CommentDTO dto);
    CommentDTO mapToCommentDTO(CommentRequest req);

    CommentGetResponse mapToCommentGetResponse(CommentDTO dto);
    CommentDTO mapToCommentDTO(CommentGetResponse req);
}
