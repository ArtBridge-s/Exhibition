package com.artbridge.exhibition.web.request;

import com.artbridge.exhibition.application.dto.ArtistDTO;
import com.artbridge.exhibition.application.dto.PeriodDTO;
import java.io.Serializable;
import lombok.Data;

@Data
public class Exhibition_POST_Req implements Serializable {

    private String title;
    private String location;
    private String content;
    private String fee;
    private String contact;
    private PeriodDTO periodDTO;
    private ArtistDTO artistDTO;
}
