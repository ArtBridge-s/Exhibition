package com.artbridge.exhibition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A View.
 */
@Document(collection = "view")
@Data
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


    public View id(String id) {
        this.setId(id);
        return this;
    }

    public View voMember(Long voMember) {
        this.setVoMember(voMember);
        return this;
    }

    public View artwork(Exhibition exhibition) {
        this.setArtwork(exhibition);
        return this;
    }

}
