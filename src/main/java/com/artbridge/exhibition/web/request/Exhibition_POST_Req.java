package com.artbridge.exhibition.web.request;

import com.artbridge.exhibition.application.dto.ArtistDTO;
import com.artbridge.exhibition.application.dto.PeriodDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class Exhibition_POST_Req implements Serializable {
    private String title;
    private String location;
    private String fee;
    private String contact;
    private PeriodDTO periodDTO;
    private ArtistDTO artistDTO;
}
