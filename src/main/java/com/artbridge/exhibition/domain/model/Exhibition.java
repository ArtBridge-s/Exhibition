package com.artbridge.exhibition.domain.model;

import com.artbridge.exhibition.domain.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Exhibition.
 */
@Document(collection = "exhibition")
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Exhibition id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Exhibition title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return this.location;
    }

    public Exhibition location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFee() {
        return this.fee;
    }

    public Exhibition fee(String fee) {
        this.setFee(fee);
        return this;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getContact() {
        return this.contact;
    }

    public Exhibition contact(String contact) {
        this.setContact(contact);
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public Exhibition imgUrl(String imgUrl) {
        this.setImgUrl(imgUrl);
        return this;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVoPeriod() {
        return this.voPeriod;
    }

    public Exhibition voPeriod(String voPeriod) {
        this.setVoPeriod(voPeriod);
        return this;
    }

    public void setVoPeriod(String voPeriod) {
        this.voPeriod = voPeriod;
    }

    public String getVoArtist() {
        return this.voArtist;
    }

    public Exhibition voArtist(String voArtist) {
        this.setVoArtist(voArtist);
        return this;
    }

    public void setVoArtist(String voArtist) {
        this.voArtist = voArtist;
    }

    public String getVoMember() {
        return this.voMember;
    }

    public Exhibition voMember(String voMember) {
        this.setVoMember(voMember);
        return this;
    }

    public void setVoMember(String voMember) {
        this.voMember = voMember;
    }

    public Status getStatus() {
        return this.status;
    }

    public Exhibition status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        if (this.comments != null) {
            this.comments.forEach(i -> i.setArtwork(null));
        }
        if (comments != null) {
            comments.forEach(i -> i.setArtwork(this));
        }
        this.comments = comments;
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

    public Set<View> getViews() {
        return this.views;
    }

    public void setViews(Set<View> views) {
        if (this.views != null) {
            this.views.forEach(i -> i.setArtwork(null));
        }
        if (views != null) {
            views.forEach(i -> i.setArtwork(this));
        }
        this.views = views;
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

    public Set<Like> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<Like> likes) {
        if (this.likes != null) {
            this.likes.forEach(i -> i.setArtwork(null));
        }
        if (likes != null) {
            likes.forEach(i -> i.setArtwork(this));
        }
        this.likes = likes;
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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exhibition)) {
            return false;
        }
        return id != null && id.equals(((Exhibition) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Exhibition{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", location='" + getLocation() + "'" +
            ", fee='" + getFee() + "'" +
            ", contact='" + getContact() + "'" +
            ", imgUrl='" + getImgUrl() + "'" +
            ", voPeriod='" + getVoPeriod() + "'" +
            ", voArtist='" + getVoArtist() + "'" +
            ", voMember='" + getVoMember() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
