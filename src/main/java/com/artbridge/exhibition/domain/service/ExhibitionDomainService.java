package com.artbridge.exhibition.domain.service;

import com.artbridge.exhibition.domain.model.Exhibition;
import org.springframework.web.multipart.MultipartFile;

public interface ExhibitionDomainService {
    Exhibition uploadImage(MultipartFile file, Exhibition exhibition);
    Exhibition initCreatedMember(Exhibition exhibition);
}
