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

    Page<ExhibitionDTO> findAllByStatus(Pageable pageable);

    /**
     * Get the "id" exhibition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExhibitionDTO> findOne(String id);

    /**
     * Delete the "id" exhibition.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
