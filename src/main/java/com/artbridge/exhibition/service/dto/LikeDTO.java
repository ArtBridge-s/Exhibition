package com.artbridge.exhibition.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.artbridge.exhibition.domain.Like} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LikeDTO implements Serializable {

    private String id;

    private Long voMember;

    private ExhibitionDTO artwork;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVoMember() {
        return voMember;
    }

    public void setVoMember(Long voMember) {
        this.voMember = voMember;
    }

    public ExhibitionDTO getArtwork() {
        return artwork;
    }

    public void setArtwork(ExhibitionDTO artwork) {
        this.artwork = artwork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LikeDTO)) {
            return false;
        }

        LikeDTO likeDTO = (LikeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, likeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LikeDTO{" +
            "id='" + getId() + "'" +
            ", voMember=" + getVoMember() +
            ", artwork=" + getArtwork() +
            "}";
    }
}
