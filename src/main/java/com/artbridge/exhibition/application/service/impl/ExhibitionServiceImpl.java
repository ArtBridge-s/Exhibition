package com.artbridge.exhibition.application.service.impl;

import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.application.mapper.ExhibitionMapper;
import com.artbridge.exhibition.application.service.ExhibitionService;
import com.artbridge.exhibition.domain.enumeration.Status;
import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.domain.service.ExhibitionDomainService;
import com.artbridge.exhibition.infrastructure.repository.ExhibitionRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link Exhibition}.
 */
@Service
public class ExhibitionServiceImpl implements ExhibitionService {

    private final Logger log = LoggerFactory.getLogger(ExhibitionServiceImpl.class);

    private final ExhibitionRepository exhibitionRepository;

    private final ExhibitionMapper exhibitionMapper;

    private final ExhibitionDomainService exhibitionDomainService;

    public ExhibitionServiceImpl(
        ExhibitionRepository exhibitionRepository,
        ExhibitionMapper exhibitionMapper,
        ExhibitionDomainService exhibitionDomainService
    ) {
        this.exhibitionRepository = exhibitionRepository;
        this.exhibitionMapper = exhibitionMapper;
        this.exhibitionDomainService = exhibitionDomainService;
    }

    @Override
    public ExhibitionDTO save(MultipartFile file, ExhibitionDTO exhibitionDTO) {
        log.debug("Request to save Exhibition : {}", exhibitionDTO);
        Exhibition exhibition = exhibitionMapper.toEntity(exhibitionDTO);

        exhibition.setStatus(Status.UPLOAD_PENDING);
        exhibition = this.exhibitionDomainService.uploadImage(file, exhibition);
        exhibition = this.exhibitionDomainService.initCreatedMember(exhibition);

        exhibition = exhibitionRepository.save(exhibition);
        return exhibitionMapper.toDto(exhibition);
    }

    @Override
    public ExhibitionDTO update(ExhibitionDTO exhibitionDTO) {
        log.debug("Request to update Exhibition : {}", exhibitionDTO);
        Exhibition exhibition = exhibitionMapper.toEntity(exhibitionDTO);
        exhibition = exhibitionRepository.save(exhibition);
        return exhibitionMapper.toDto(exhibition);
    }

    @Override
    public Optional<ExhibitionDTO> partialUpdate(ExhibitionDTO exhibitionDTO) {
        log.debug("Request to partially update Exhibition : {}", exhibitionDTO);

        return exhibitionRepository
            .findById(exhibitionDTO.getId())
            .map(existingExhibition -> {
                exhibitionMapper.partialUpdate(existingExhibition, exhibitionDTO);

                return existingExhibition;
            })
            .map(exhibitionRepository::save)
            .map(exhibitionMapper::toDto);
    }

    @Override
    public Optional<ExhibitionDTO> findOneStatusOK(String id) {
        log.debug("Request to get Exhibition : {}", id);
        return exhibitionRepository.findByIdAndStatus(id, Status.OK).map(exhibitionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Exhibition : {}", id);
        exhibitionRepository.deleteById(id);
    }

    @Override
    public Page<ExhibitionDTO> findAllByStatus_ok(Pageable pageable) {
        log.debug("Status가 OK인 모든 전시회를 가져옵니다.");
        return exhibitionRepository.findAllByStatusOrderByIdDesc(Status.OK, pageable).map(exhibitionMapper::toDto);
    }

    @Override
    public Page<ExhibitionDTO> findAllByStatus_upload(Pageable pageable) {
        log.debug("Status가 UPLOAD_PENDING인 모든 전시회를 가져옵니다.");
        return exhibitionRepository.findAllByStatusOrderByIdDesc(Status.UPLOAD_PENDING, pageable).map(exhibitionMapper::toDto);
    }

    @Override
    public Page<ExhibitionDTO> findAllByStatus_revision(Pageable pageable) {
        log.debug("Status가 REVISION_PENDING인 모든 전시회를 가져옵니다.");
        return exhibitionRepository.findAllByStatusOrderByIdDesc(Status.REVISION_PENDING, pageable).map(exhibitionMapper::toDto);
    }

    @Override
    public Page<ExhibitionDTO> findAllByStatus_delete(Pageable pageable) {
        log.debug("Status가 DELETE_PENDING인 모든 전시회를 가져옵니다.");
        return exhibitionRepository.findAllByStatusOrderByIdDesc(Status.DELETE_PENDING, pageable).map(exhibitionMapper::toDto);
    }

    @Override
    public Optional<ExhibitionDTO> updateByAdmin(String id, ExhibitionDTO exhibitionDTO) {
        log.debug("Request to update Exhibition : {}", exhibitionDTO);
        return exhibitionRepository
            .findById(id)
            .map(existingExhibition -> {
                exhibitionMapper.partialUpdate(existingExhibition, exhibitionDTO);

                return existingExhibition;
            })
            .map(exhibitionRepository::save)
            .map(exhibitionMapper::toDto);
    }

    @Override
    public Optional<ExhibitionDTO> requestRevision(String id, ExhibitionDTO exhibitionDTO) {
        log.debug("Request to update Exhibition : {}", exhibitionDTO);
        return exhibitionRepository
            .findById(id)
            .map(existingExhibition -> {
                exhibitionMapper.partialUpdate(existingExhibition, exhibitionDTO);
                existingExhibition.setStatus(Status.REVISION_PENDING);
                return existingExhibition;
            })
            .map(exhibitionRepository::save)
            .map(exhibitionMapper::toDto);
    }

    @Override
    public Optional<ExhibitionDTO> requestDelete(String id) {
        log.debug("Request to update Exhibition : {}", id);
        return exhibitionRepository
            .findById(id)
            .map(existingExhibition -> {
                existingExhibition.setStatus(Status.DELETE_PENDING);
                return existingExhibition;
            })
            .map(exhibitionRepository::save)
            .map(exhibitionMapper::toDto);
    }

    @Override
    public Optional<ExhibitionDTO> requestOk(String id) {
        log.debug("Request to update Exhibition : {}", id);
        return exhibitionRepository
            .findById(id)
            .map(existingExhibition -> {
                existingExhibition.setStatus(Status.OK);
                return existingExhibition;
            })
            .map(exhibitionRepository::save)
            .map(exhibitionMapper::toDto);
    }

    @Override
    public void authorizeDelete(String id) {
        log.debug("Request to delete Exhibition : {}", id);/*TODO*/
        exhibitionRepository.deleteById(id);
    }

    @Override
    public void deleteByAdmin(String id) {
        log.debug("Request to delete Exhibition by Admin: {}", id);
        exhibitionRepository.deleteById(id);
    }

    @Override
    public ExhibitionDTO saveByAdmin(MultipartFile file, ExhibitionDTO exhibitionDTO) {
        log.debug("Request to save Exhibition by Admin: {}", exhibitionDTO);
        Exhibition exhibition = exhibitionMapper.toEntity(exhibitionDTO);

        exhibition.setStatus(Status.OK);
        exhibition = this.exhibitionDomainService.uploadImage(file, exhibition);
        exhibition = this.exhibitionDomainService.initCreatedMember(exhibition);

        exhibition = exhibitionRepository.save(exhibition);
        return exhibitionMapper.toDto(exhibition);
    }
}
