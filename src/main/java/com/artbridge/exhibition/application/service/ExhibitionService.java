package com.artbridge.exhibition.application.service;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Exhibition}.
 */
public interface ExhibitionService {
    ExhibitionDTO save(MultipartFile file, ExhibitionDTO exhibitionDTO);

    /**
     * Updates a exhibition.
     *
     * @param exhibitionDTO the entity to update.
     * @return the persisted entity.
     */
    ExhibitionDTO update(ExhibitionDTO exhibitionDTO);

    /**
     * Partially updates a exhibition.
     *
     * @param exhibitionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ExhibitionDTO> partialUpdate(ExhibitionDTO exhibitionDTO);

    Page<ExhibitionDTO> findAllByStatus_ok(Pageable pageable);

    /**
     * Get the "id" exhibition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExhibitionDTO> findOneStatusOK(String id);

    /**
     * Delete the "id" exhibition.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Page<ExhibitionDTO> findAllByStatus_upload(Pageable pageable);

    Page<ExhibitionDTO> findAllByStatus_revision(Pageable pageable);

    Page<ExhibitionDTO> findAllByStatus_delete(Pageable pageable);

    Optional<ExhibitionDTO> updateByAdmin(String id, ExhibitionDTO exhibitionDTO);

    Optional<ExhibitionDTO> requestRevision(String id, ExhibitionDTO dto);

    Optional<ExhibitionDTO> requestDelete(String id);

    Optional<ExhibitionDTO> requestOk(String id);

    void authorizeDelete(String id);

    void deleteByAdmin(String id);

    ExhibitionDTO saveByAdmin(MultipartFile file, ExhibitionDTO exhibitionDTO);
}
