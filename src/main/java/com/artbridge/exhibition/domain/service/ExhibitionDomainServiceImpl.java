package com.artbridge.exhibition.domain.service;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.infrastructure.gcs.GCSService;
import com.artbridge.exhibition.infrastructure.security.SecurityUtils;
import com.artbridge.exhibition.infrastructure.security.jwt.TokenProvider;
import com.artbridge.exhibition.web.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ExhibitionDomainServiceImpl implements ExhibitionDomainService {
    private final GCSService gcsService;
    private final TokenProvider tokenProvider;

    public ExhibitionDomainServiceImpl(GCSService gcsService, TokenProvider tokenProvider) {
        this.gcsService = gcsService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Exhibition uploadImage(MultipartFile file, Exhibition exhibition) {
        log.debug("REST request to upload image file : {}", file);
        if (!Objects.isNull(file)) {
            try {
                String imgUrl = this.gcsService.uploadImageToGCS(file);
                exhibition.setImgUrl(imgUrl);
            } catch (IOException e) {
                throw new BadRequestAlertException("File upload failed", "Exhibition", "filereadfailed");
            }
        }
        return exhibition;
    }

    @Override
    public Exhibition initCreatedMember(Exhibition exhibition) {
        Optional<String> optToken = SecurityUtils.getCurrentUserJWT();
        if (optToken.isEmpty() || !this.tokenProvider.validateToken(optToken.get())) {
            throw new BadRequestAlertException("Invalid token", "Exhibition", "invalidtoken");
        }

        Authentication authentication = this.tokenProvider.getAuthentication(optToken.get());
        String login = authentication.getName();

        Long id = this.tokenProvider.getUserIdFromToken(optToken.get());

        exhibition.setCreatedMember(id, login);

        return exhibition;
    }

}
