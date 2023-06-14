package com.artbridge.exhibition.domain.model;

import com.artbridge.exhibition.domain.enumeration.Status;
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
@SuppressWarnings("common-java:DuplicatedBlocks")
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

    @Field("vo_period")
    private String voPeriod;

    @Field("vo_artist")
    private String voArtist;

    @Field("vo_member")
    private String voMember;

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


    public Exhibition id(String id) {
        this.setId(id);
        return this;
    }

    public Exhibition title(String title) {
        this.setTitle(title);
        return this;
    }

    public Exhibition location(String location) {
        this.setLocation(location);
        return this;
    }

    public Exhibition fee(String fee) {
        this.setFee(fee);
        return this;
    }

    public Exhibition contact(String contact) {
        this.setContact(contact);
        return this;
    }

    public Exhibition imgUrl(String imgUrl) {
        this.setImgUrl(imgUrl);
        return this;
    }

    public Exhibition voPeriod(String voPeriod) {
        this.setVoPeriod(voPeriod);
        return this;
    }

    public Exhibition voArtist(String voArtist) {
        this.setVoArtist(voArtist);
        return this;
    }

    public Exhibition voMember(String voMember) {
        this.setVoMember(voMember);
        return this;
    }

    public Exhibition status(Status status) {
        this.setStatus(status);
        return this;
    }

    public Exhibition comments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public Exhibition addComments(Comment comment) {
        this.comments.add(comment);
        comment.setArtwork(this);
        return this;
    }

    public Exhibition removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setArtwork(null);
        return this;
    }


    public Exhibition views(Set<View> views) {
        this.setViews(views);
        return this;
    }

    public Exhibition addViews(View view) {
        this.views.add(view);
        view.setArtwork(this);
        return this;
    }

    public Exhibition removeViews(View view) {
        this.views.remove(view);
        view.setArtwork(null);
        return this;
    }


    public Exhibition likes(Set<Like> likes) {
        this.setLikes(likes);
        return this;
    }

    public Exhibition addLikes(Like like) {
        this.likes.add(like);
        like.setArtwork(this);
        return this;
    }

    public Exhibition removeLikes(Like like) {
        this.likes.remove(like);
        like.setArtwork(null);
        return this;
    }
}
