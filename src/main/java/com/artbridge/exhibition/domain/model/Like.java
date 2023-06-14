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
 * A Like.
 */
@Document(collection = "like")
@Data
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("member")
    private Member createdMember;

    @DBRef
    @Field("exhibition")
    @JsonIgnoreProperties(value = { "comments", "views", "likes" }, allowSetters = true)
    private Exhibition exhibition;

}
