package com.artbridge.exhibition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Comment.
 */
@Document(collection = "comment")
@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("vo_member")
    private Long voMember;

    @Field("content")
    private String content;

    @DBRef
    @Field("artwork")
    @JsonIgnoreProperties(value = { "comments", "views", "likes" }, allowSetters = true)
    private Exhibition artwork;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Comment id(String id) {
        this.setId(id);
        return this;
    }


    public Comment voMember(Long voMember) {
        this.setVoMember(voMember);
        return this;
    }


    public Comment content(String content) {
        this.setContent(content);
        return this;
    }


    public Comment artwork(Exhibition exhibition) {
        this.setArtwork(exhibition);
        return this;
    }

}
