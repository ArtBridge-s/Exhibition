package com.artbridge.exhibition.service.dto;

import java.io.Serializable;

import com.artbridge.exhibition.domain.model.View;
import lombok.Data;

/**
 * A DTO for the {@link View} entity.
 */
@Data
public class ViewDTO implements Serializable {

    private String id;
    private Long voMember;
    private ExhibitionDTO artwork;
}
