package com.artbridge.exhibition.web.response;

import com.artbridge.exhibition.application.dto.MemberDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentGetResponse implements Serializable {
    private String id;
    private MemberDTO createdMemberDTO;
    private String content;
}
