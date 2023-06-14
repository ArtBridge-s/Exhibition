package com.artbridge.exhibition.application.dto;

import com.artbridge.exhibition.domain.model.View;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link View} entity.
 */
@Data
public class ViewDTO implements Serializable {

    private String id;
    private MemberDTO memberDTO;
    private ExhibitionDTO exhibitionDTO;
}
