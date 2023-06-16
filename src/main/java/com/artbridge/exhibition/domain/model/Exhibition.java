package com.artbridge.exhibition.domain.model;

import com.artbridge.exhibition.domain.enumeration.Status;
import com.artbridge.exhibition.domain.valueobject.Artist;
import com.artbridge.exhibition.domain.valueobject.Member;
import com.artbridge.exhibition.domain.valueobject.Period;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Exhibition.
 */
@Document(collection = "exhibition")
@Data
public class Exhibition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("location")
    private String location;

    @Field("fee")
    private String fee;

    @Field("contact")
    private String contact;

    @Field("img_url")
    private String imgUrl;

    @Field("period")
    private Period period;

    @Field("artist")
    private Artist artist;

    @Field("member")
    private Member createdMember;

    @Field("status")
    private Status status;

    @DBRef
    @Field("comments")
    @JsonIgnoreProperties(value = { "artwork" }, allowSetters = true)
    private Set<Comment> comments = new HashSet<>();

    @DBRef
    @Field("views")
    @JsonIgnoreProperties(value = { "artwork" }, allowSetters = true)
    private Set<View> views = new HashSet<>();

    @DBRef
    @Field("likes")
    @JsonIgnoreProperties(value = { "artwork" }, allowSetters = true)
    private Set<Like> likes = new HashSet<>();

    public void setCreatedMember(Long id, String login) {
        this.createdMember = new Member(id, login);
    }
}
