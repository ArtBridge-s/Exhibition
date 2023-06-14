package com.artbridge.exhibition.service.dto;

import com.artbridge.exhibition.domain.enumeration.Status;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.artbridge.exhibition.domain.Exhibition} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ExhibitionDTO implements Serializable {

    private String id;
    private String title;
    private String location;
    private String fee;
    private String contact;
    private String imgUrl;
    private String voPeriod;
    private String voArtist;
    private String voMember;
    private Status status;
}
