package com.artbridge.exhibition.service.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class MemberDTO implements Serializable {

    private Long id;
    private String login;
    private String name;
}
