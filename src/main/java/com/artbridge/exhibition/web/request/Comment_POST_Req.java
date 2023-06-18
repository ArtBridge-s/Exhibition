package com.artbridge.exhibition.web.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment_POST_Req implements Serializable {
    private String content;
}
