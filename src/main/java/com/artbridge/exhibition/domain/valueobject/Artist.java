package com.artbridge.exhibition.domain.valueobject;

import io.mongock.utils.field.Field;
import java.io.Serializable;
import lombok.Data;

@Data
public class Artist implements Serializable {

    @Field("artist_name")
    private String name;
}
