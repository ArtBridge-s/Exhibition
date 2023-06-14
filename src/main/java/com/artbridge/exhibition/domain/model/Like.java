package com.artbridge.exhibition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Like.
 */
@Document(collection = "like")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("vo_member")
    private Long voMember;

    @DBRef
    @Field("artwork")
    @JsonIgnoreProperties(value = { "comments", "views", "likes" }, allowSetters = true)
    private Exhibition artwork;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Like id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVoMember() {
        return this.voMember;
    }

    public Like voMember(Long voMember) {
        this.setVoMember(voMember);
        return this;
    }

    public void setVoMember(Long voMember) {
        this.voMember = voMember;
    }

    public Exhibition getArtwork() {
        return this.artwork;
    }

    public void setArtwork(Exhibition exhibition) {
        this.artwork = exhibition;
    }

    public Like artwork(Exhibition exhibition) {
        this.setArtwork(exhibition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Like)) {
            return false;
        }
        return id != null && id.equals(((Like) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Like{" +
            "id=" + getId() +
            ", voMember=" + getVoMember() +
            "}";
    }
}
