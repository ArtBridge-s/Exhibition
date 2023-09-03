package com.artbridge.exhibition.web.mapper;


import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.web.request.AdminExhibitionRequest;
import com.artbridge.exhibition.web.request.ExhibitionRevisionRequest;
import com.artbridge.exhibition.web.request.ExhibitionRequest;
import com.artbridge.exhibition.web.response.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExhibitionWebMapper {
    ExhibitionRequest mapToExhibitionRequest(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(ExhibitionRequest req);

    ActiveExhibition mapToActiveExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(ActiveExhibition req);

    PendingUploadExhibition mapToPendingUploadExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(PendingUploadExhibition req);

    PendingDeleteExhibition mapToPendingDeleteExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(PendingDeleteExhibition req);

    PendingRevisionExhibition mapToPendingRevisionExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(PendingRevisionExhibition req);

    AdminExhibitionRequest mapToAdminExhibitionRequest(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(AdminExhibitionRequest req);

    ExhibitionRevisionRequest mapToExhibitionRevisionRequest(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(ExhibitionRevisionRequest req);

    ExhibitionDetails mapToExhibitionDetails(ExhibitionDTO exhibitionDTO);
    ExhibitionDetails mapToExhibitionDTO(ExhibitionDetails exhibitionDetails);
}
