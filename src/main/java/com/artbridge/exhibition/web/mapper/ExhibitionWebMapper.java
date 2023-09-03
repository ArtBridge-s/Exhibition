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

    ActiveExhibitionResponse mapToActiveExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(ActiveExhibitionResponse req);

    PendingUploadExhibitionResponse mapToPendingUploadExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(PendingUploadExhibitionResponse req);

    PendingDeleteExhibitionResponse mapToPendingDeleteExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(PendingDeleteExhibitionResponse req);

    PendingRevisionExhibitionResponse mapToPendingRevisionExhibition(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(PendingRevisionExhibitionResponse req);

    AdminExhibitionRequest mapToAdminExhibitionRequest(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(AdminExhibitionRequest req);

    ExhibitionRevisionRequest mapToExhibitionRevisionRequest(ExhibitionDTO dto);
    ExhibitionDTO mapToExhibitionDTO(ExhibitionRevisionRequest req);

    ExhibitionDetailsResponse mapToExhibitionDetails(ExhibitionDTO exhibitionDTO);
    ExhibitionDetailsResponse mapToExhibitionDTO(ExhibitionDetailsResponse exhibitionDetailsResponse);
}
