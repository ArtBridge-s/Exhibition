package com.artbridge.exhibition.web.rest;

import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.infrastructure.repository.ExhibitionRepository;
import com.artbridge.exhibition.application.service.ExhibitionService;
import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.web.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.artbridge.exhibition.web.mapper.Exhibition_GET_LIST_STATUS_OK_Res_Mapper;
import com.artbridge.exhibition.web.mapper.Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res_Mapper;
import com.artbridge.exhibition.web.mapper.Exhibition_POST_Req_Mapper;
import com.artbridge.exhibition.web.request.Exhibition_POST_Req;
import com.artbridge.exhibition.web.response.Exhibition_GET_LIST_STATUS_OK_Res;
import com.artbridge.exhibition.web.response.Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link Exhibition}.
 */
@RestController
@RequestMapping("/api")
public class ExhibitionResource {

    private final Logger log = LoggerFactory.getLogger(ExhibitionResource.class);

    private static final String ENTITY_NAME = "exhibitionExhibition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExhibitionService exhibitionService;

    private final ExhibitionRepository exhibitionRepository;


    private final Exhibition_POST_Req_Mapper exhibitionPostReqMapper;
    private final Exhibition_GET_LIST_STATUS_OK_Res_Mapper exhibition_get_list_status_ok_res_mapper;
    private final Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res_Mapper exhibitionGetListStatusUploadPendingResMapper;

    public ExhibitionResource(ExhibitionService exhibitionService, ExhibitionRepository exhibitionRepository, Exhibition_POST_Req_Mapper exhibitionPostReqMapper, Exhibition_GET_LIST_STATUS_OK_Res_Mapper exhibitionGetListStatusOkResMapper, Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res_Mapper exhibitionGetListStatusUploadPendingResMapper) {
        this.exhibitionService = exhibitionService;
        this.exhibitionRepository = exhibitionRepository;
        this.exhibitionPostReqMapper = exhibitionPostReqMapper;
        this.exhibition_get_list_status_ok_res_mapper = exhibitionGetListStatusOkResMapper;
        this.exhibitionGetListStatusUploadPendingResMapper = exhibitionGetListStatusUploadPendingResMapper;
    }

    @PostMapping("/exhibitions")
    public ResponseEntity<ExhibitionDTO> createExhibition(@RequestParam("image") MultipartFile file, @RequestParam("exhibition_post_req") String exhibition_post_req_str) throws URISyntaxException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Exhibition_POST_Req exhibition_post_req = mapper.readValue(exhibition_post_req_str, Exhibition_POST_Req.class);

        log.debug("REST request to save Exhibition : {}", exhibition_post_req);

        ExhibitionDTO exhibitionDTO = exhibitionPostReqMapper.toDto(exhibition_post_req);
        ExhibitionDTO result = exhibitionService.save(file, exhibitionDTO);

        return ResponseEntity
            .created(new URI("/api/exhibitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }


    /**
     * {@code PUT  /exhibitions/:id} : Updates an existing exhibition.
     *
     * @param id the id of the exhibitionDTO to save.
     * @param exhibitionDTO the exhibitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exhibitionDTO,
     * or with status {@code 400 (Bad Request)} if the exhibitionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exhibitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exhibitions/{id}")
    public ResponseEntity<ExhibitionDTO> updateExhibition(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ExhibitionDTO exhibitionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Exhibition : {}, {}", id, exhibitionDTO);
        if (exhibitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exhibitionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exhibitionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ExhibitionDTO result = exhibitionService.update(exhibitionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, exhibitionDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /exhibitions/:id} : Partial updates given fields of an existing exhibition, field will ignore if it is null
     *
     * @param id the id of the exhibitionDTO to save.
     * @param exhibitionDTO the exhibitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exhibitionDTO,
     * or with status {@code 400 (Bad Request)} if the exhibitionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the exhibitionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the exhibitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/exhibitions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExhibitionDTO> partialUpdateExhibition(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ExhibitionDTO exhibitionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Exhibition partially : {}, {}", id, exhibitionDTO);
        if (exhibitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exhibitionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exhibitionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ExhibitionDTO> result = exhibitionService.partialUpdate(exhibitionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, exhibitionDTO.getId())
        );
    }


    @GetMapping("/exhibitions")
    public ResponseEntity<List<Exhibition_GET_LIST_STATUS_OK_Res>> getAllExhibitionsByStatusOK(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Exhibitions");
        Page<ExhibitionDTO> page = exhibitionService.findAllByStatus_ok(pageable);

        Page<Exhibition_GET_LIST_STATUS_OK_Res> exhibition_get_list_status_ok_res_page = page.map(exhibition_get_list_status_ok_res_mapper::toRes);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(exhibition_get_list_status_ok_res_page.getContent());

    }


    @GetMapping("/exhibitions/pendings")
    public ResponseEntity<List<Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res>> getAllExhibitionsByStatusUpload(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Exhibitions");
        Page<ExhibitionDTO> page = exhibitionService.findAllByStatus_upload(pageable);

        Page<Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res> exhibition_get_list_status_ok_res_page = page.map(exhibitionGetListStatusUploadPendingResMapper::toRes);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(exhibition_get_list_status_ok_res_page.getContent());
    }


    /**
     * {@code GET  /exhibitions/:id} : get the "id" exhibition.
     *
     * @param id the id of the exhibitionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exhibitionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exhibitions/{id}")
    public ResponseEntity<ExhibitionDTO> getExhibition(@PathVariable String id) {
        log.debug("REST request to get Exhibition : {}", id);
        Optional<ExhibitionDTO> exhibitionDTO = exhibitionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exhibitionDTO);
    }

    /**
     * {@code DELETE  /exhibitions/:id} : delete the "id" exhibition.
     *
     * @param id the id of the exhibitionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exhibitions/{id}")
    public ResponseEntity<Void> deleteExhibition(@PathVariable String id) {
        log.debug("REST request to delete Exhibition : {}", id);
        exhibitionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
