package com.artbridge.exhibition.domain.model;

import com.artbridge.exhibition.domain.valueobject.Member;
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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("member")
    private Member createdMember;

    @Field("content")
    private String content;

    @DBRef
    @Field("exhibition")
    @JsonIgnoreProperties(value = { "comments", "views", "likes" }, allowSetters = true)
    private Exhibition exhibition;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Comment id(String id) {
        this.setId(id);
        return this;
    }


    public Comment voMember(Member voMember) {
        this.setCreatedMember(voMember);
        return this;
    }


    public Comment content(String content) {
        this.setContent(content);
        return this;
    }


    public Comment artwork(Exhibition exhibition) {
        this.setExhibition(exhibition);
        return this;
    }

}
