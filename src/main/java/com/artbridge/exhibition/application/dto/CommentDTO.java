package com.artbridge.exhibition.application.dto;

import java.io.Serializable;

import com.artbridge.exhibition.domain.model.Comment;
import lombok.Data;

/**
 * A DTO for the {@link Comment} entity.
 */
@Data
public class CommentDTO implements Serializable {

    private String id;
    private MemberDTO createdMemberDTO;
    private String content;
    private ExhibitionDTO exhibitionDTO;
}
