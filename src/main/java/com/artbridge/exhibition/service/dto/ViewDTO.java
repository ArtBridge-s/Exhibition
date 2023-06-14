package com.artbridge.exhibition.service.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.artbridge.exhibition.domain.View} entity.
 */
@Data
public class ViewDTO implements Serializable {

    private String id;
    private Long voMember;
    private ExhibitionDTO artwork;
}
