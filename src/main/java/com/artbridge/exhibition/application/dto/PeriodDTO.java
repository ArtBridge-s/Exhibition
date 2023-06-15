package com.artbridge.exhibition.application.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PeriodDTO implements Serializable {
    private String startDate;
    private String endDate;
    private String time;
}
