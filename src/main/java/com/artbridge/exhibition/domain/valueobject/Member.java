package com.artbridge.exhibition.domain.valueobject;

import io.mongock.utils.field.Field;
import java.io.Serializable;
import lombok.Data;

@Data
public class Member implements Serializable {

    @Field("member_id")
    private Long id;

    @Field("member_login")
    private String login;

    @Field("member_name")
    private String name;
}
