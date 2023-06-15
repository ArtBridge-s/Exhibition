package com.artbridge.exhibition.domain.valueobject;

import io.mongock.utils.field.Field;
import lombok.Data;

import java.io.Serializable;

@Data
public class Period implements Serializable {

    @Field("start_date")
    private String startDate;

    @Field("end_date")
    private String endDate;

    @Field("time")
    private String time;
}
