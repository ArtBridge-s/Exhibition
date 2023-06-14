package com.artbridge.exhibition.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A View.
 */
@Document(collection = "view")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class View implements Serializable {

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

    public View id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVoMember() {
        return this.voMember;
    }

    public View voMember(Long voMember) {
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

    public View artwork(Exhibition exhibition) {
        this.setArtwork(exhibition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof View)) {
            return false;
        }
        return id != null && id.equals(((View) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "View{" +
            "id=" + getId() +
            ", voMember=" + getVoMember() +
            "}";
    }
}
