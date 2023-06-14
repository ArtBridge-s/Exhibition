package com.artbridge.exhibition.service.dto;

import java.io.Serializable;

import com.artbridge.exhibition.domain.model.Like;
import lombok.Data;

/**
 * A DTO for the {@link Like} entity.
 */
@Data
public class LikeDTO implements Serializable {

    private String id;
    private MemberDTO memberDTO;
    private ExhibitionDTO exhibitionDTO;
}
