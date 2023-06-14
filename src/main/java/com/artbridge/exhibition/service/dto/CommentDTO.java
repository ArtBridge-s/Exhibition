package com.artbridge.exhibition.service.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.artbridge.exhibition.domain.Comment} entity.
 */
@Data
public class CommentDTO implements Serializable {

    private String id;
    private Long voMember;
    private String content;
    private ExhibitionDTO artwork;
}
