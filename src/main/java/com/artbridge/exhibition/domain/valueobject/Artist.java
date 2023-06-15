package com.artbridge.exhibition.domain.valueobject;

import io.mongock.utils.field.Field;
import lombok.Data;

@Data
public class Artist {

    @Field("artist_name")
    private String name;
}
