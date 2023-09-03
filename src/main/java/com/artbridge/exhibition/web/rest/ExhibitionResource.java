package com.artbridge.exhibition.web.rest;

import com.artbridge.exhibition.application.dto.ExhibitionDTO;
import com.artbridge.exhibition.application.service.ExhibitionService;
import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.infrastructure.repository.ExhibitionRepository;
import com.artbridge.exhibition.web.request.AdminExhibitionRequest;
import com.artbridge.exhibition.web.request.ExhibitionRevisionRequest;
import com.artbridge.exhibition.web.request.ExhibitionRequest;
import com.artbridge.exhibition.web.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ExhibitionResource {

    private static final String ENTITY_NAME = "exhibitionExhibition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExhibitionService exhibitionService;

    private final ExhibitionRepository exhibitionRepository;

    private final Exhibition_POST_Req_Mapper exhibitionPostReqMapper;
    private final Exhibition_GET_LIST_STATUS_OK_Res_Mapper exhibitionGetListStatusOkResMapper;
    private final Exhibition_GET_LIST_STATUS_UPLOAD_PENDING_Res_Mapper exhibitionGetListStatusUploadPendingResMapper;
    private final Exhibition_GET_LIST_STATUS_DELETE_PENDING_Res_Mapper exhibitionGetListStatusDeletePendingResMapper;
    private final Exhibition_GET_LIST_STATUS_REVISION_PENDING_Res_Mapper exhibitionGetListStatusRevisionPendingResMapper;
    private final ExhibitionByAdminReq_Mapper exhibitionByAdminReqMapper;
    private final ExhibitionRevisionRequest_Req_Mapper exhibitionRevisionRequestReqMapper;
    private final Exhibition_GET_Res_Mapper exhibitionGetResMapper;

    @PostMapping("/exhibitions")
    public ResponseEntity<ExhibitionDTO> createExhibition(@RequestParam("image") MultipartFile file, @RequestParam("exhibition_post_req") String exhibition_post_req_str) throws URISyntaxException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExhibitionRequest exhibition_request = mapper.readValue(exhibition_post_req_str, ExhibitionRequest.class);

        log.debug("REST request to save Exhibition : {}", exhibition_request);

        ExhibitionDTO exhibitionDTO = exhibitionPostReqMapper.toDto(exhibition_request);
        ExhibitionDTO result = exhibitionService.save(file, exhibitionDTO);

        return ResponseEntity.created(new URI("/api/exhibitions/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId())).body(result);
    }


    @GetMapping("/exhibitions/status/ok")
    public ResponseEntity<List<ActiveExhibition>> getAllExhibitionsByStatusOK(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Exhibitions");
        Page<ExhibitionDTO> page = exhibitionService.findAllByStatus_ok(pageable);

        Page<ActiveExhibition> exhibition_get_list_status_ok_res_page = page.map(exhibitionGetListStatusOkResMapper::toRes);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(exhibition_get_list_status_ok_res_page.getContent());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/exhibitions/status/upload")
    public ResponseEntity<List<PendingUploadExhibition>> getAllExhibitionsByStatusUpload(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Exhibitions");
        Page<ExhibitionDTO> page = exhibitionService.findAllByStatus_upload(pageable);

        Page<PendingUploadExhibition> exhibitionGetListStatusUploadPendingRes = page.map(exhibitionGetListStatusUploadPendingResMapper::toRes);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(exhibitionGetListStatusUploadPendingRes.getContent());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/exhibitions/status/revision")
    public ResponseEntity<List<PendingRevisionExhibition>> getAllExhibitionsByStatusRevision(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Exhibitions");
        Page<ExhibitionDTO> page = exhibitionService.findAllByStatus_revision(pageable);

        Page<PendingRevisionExhibition> exhibitionGetListStatusRevisionPendingRes = page.map(exhibitionGetListStatusRevisionPendingResMapper::toRes);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(exhibitionGetListStatusRevisionPendingRes.getContent());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/exhibitions/status/delete")
    public ResponseEntity<List<PendingDeleteExhibition>> getAllExhibitionsByStatusDelete(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Exhibitions");
        Page<ExhibitionDTO> page = exhibitionService.findAllByStatus_delete(pageable);

        Page<PendingDeleteExhibition> exhibition_get_list_status_ok_res_page = page.map(exhibitionGetListStatusDeletePendingResMapper::toRes);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(exhibition_get_list_status_ok_res_page.getContent());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/exhibitions/admin")
    public ResponseEntity<ExhibitionDTO> createExhibitionByAdmin(@RequestParam("image") MultipartFile file, @RequestParam("exhibition_post_req") String exhibition_post_req_str) throws URISyntaxException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExhibitionRequest exhibition_request = mapper.readValue(exhibition_post_req_str, ExhibitionRequest.class);

        log.debug("REST request to save Exhibition : {}", exhibition_request);

        ExhibitionDTO exhibitionDTO = exhibitionPostReqMapper.toDto(exhibition_request);
        ExhibitionDTO result = exhibitionService.saveByAdmin(file, exhibitionDTO);

        return ResponseEntity.created(new URI("/api/exhibitions/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId())).body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/exhibitions/admin/{id}")
    public ResponseEntity<ExhibitionDTO> updateExhibitionByAdmin(@PathVariable(value = "id") final String id, @RequestBody AdminExhibitionRequest adminExhibitionRequest) {
        log.debug("REST request to update Exhibition : {}, {}", id, adminExhibitionRequest);

        Optional<ExhibitionDTO> result = exhibitionService.updateByAdmin(id, exhibitionByAdminReqMapper.toDto(adminExhibitionRequest));
        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/exhibitions/admin/{id}")
    public ResponseEntity<Void> deleteExhibitionByAdmin(@PathVariable(value = "id") final String id) {
        log.debug("REST request to delete Exhibition : {}", id);

        exhibitionService.deleteByAdmin(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    @PutMapping("/exhibitions/request/revision/{id}")
    public ResponseEntity<ExhibitionDTO> requestRevision(@PathVariable(value = "id") final String id, @RequestBody ExhibitionRevisionRequest exhibitionRevisionRequestReq) {
        log.debug("REST request to update Exhibition : {}", id);

        Optional<ExhibitionDTO> result = exhibitionService.requestRevision(id, exhibitionRevisionRequestReqMapper.toDto(exhibitionRevisionRequestReq));
        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id));
    }

    @PatchMapping("/exhibitions/request/delete/{id}")
    public ResponseEntity<ExhibitionDTO> requestDelete(@PathVariable(value = "id") final String id) {
        log.debug("REST request to update Exhibition : {}", id);

        Optional<ExhibitionDTO> result = exhibitionService.requestDelete(id);
        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/exhibitions/authorize/ok/{id}")
    public ResponseEntity<ExhibitionDTO> requestOk(@PathVariable(value = "id") final String id) {
        log.debug("REST request to update Exhibition : {}", id);

        Optional<ExhibitionDTO> result = exhibitionService.requestOk(id);
        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/exhibitions/authorize/delete/{id}")
    public ResponseEntity<Void> authorizeDelete(@PathVariable(value = "id") final String id) {
        log.debug("REST request to delete Exhibition : {}", id);
        exhibitionService.authorizeDelete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    @GetMapping("/exhibitions/{id}")
    public ResponseEntity<ExhibitionDetails> getExhibitionByStatus_OK(@PathVariable(value = "id") final String id) {
        log.debug("특정 전시회 조회");
        Optional<ExhibitionDTO> exhibitionDTO = exhibitionService.findOneStatusOK(id);
        return ResponseUtil.wrapOrNotFound(exhibitionDTO.map(exhibitionGetResMapper::toRes));
    }

}
