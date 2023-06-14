package com.artbridge.exhibition.service.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.artbridge.exhibition.domain.Like} entity.
 */
@Data
public class LikeDTO implements Serializable {

    private String id;
    private Long voMember;
    private ExhibitionDTO artwork;
}
