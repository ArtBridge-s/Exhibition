package com.artbridge.exhibition.web.response;

import com.artbridge.exhibition.application.dto.ArtistDTO;
import com.artbridge.exhibition.application.dto.MemberDTO;
import com.artbridge.exhibition.application.dto.PeriodDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class Exhibition_GET_Res implements Serializable {
    private String id;
    private String title;
    private String location;
    private String fee;
    private String contact;
    private String imgUrl;
    private PeriodDTO periodDTO;
    private ArtistDTO artistDTO;
    private MemberDTO createdMemberDTO;
}
