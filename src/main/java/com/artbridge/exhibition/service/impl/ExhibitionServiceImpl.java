package com.artbridge.exhibition.service.impl;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.repository.ExhibitionRepository;
import com.artbridge.exhibition.service.ExhibitionService;
import com.artbridge.exhibition.service.dto.ExhibitionDTO;
import com.artbridge.exhibition.service.mapper.ExhibitionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Exhibition}.
 */
@Service
public class ExhibitionServiceImpl implements ExhibitionService {

    private final Logger log = LoggerFactory.getLogger(ExhibitionServiceImpl.class);

    private final ExhibitionRepository exhibitionRepository;

    private final ExhibitionMapper exhibitionMapper;

    public ExhibitionServiceImpl(ExhibitionRepository exhibitionRepository, ExhibitionMapper exhibitionMapper) {
        this.exhibitionRepository = exhibitionRepository;
        this.exhibitionMapper = exhibitionMapper;
    }

    @Override
    public ExhibitionDTO save(ExhibitionDTO exhibitionDTO) {
        log.debug("Request to save Exhibition : {}", exhibitionDTO);
        Exhibition exhibition = exhibitionMapper.toEntity(exhibitionDTO);
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
    public Page<ExhibitionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Exhibitions");
        return exhibitionRepository.findAll(pageable).map(exhibitionMapper::toDto);
    }

    @Override
    public Optional<ExhibitionDTO> findOne(String id) {
        log.debug("Request to get Exhibition : {}", id);
        return exhibitionRepository.findById(id).map(exhibitionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Exhibition : {}", id);
        exhibitionRepository.deleteById(id);
    }
}
