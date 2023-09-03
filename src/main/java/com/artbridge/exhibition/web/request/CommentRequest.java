package com.artbridge.exhibition.web.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentRequest implements Serializable {
    private String content;
}
