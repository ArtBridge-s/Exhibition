package com.artbridge.exhibition.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.artbridge.exhibition.IntegrationTest;
import com.artbridge.exhibition.domain.model.View;
import com.artbridge.exhibition.repository.ViewRepository;
import com.artbridge.exhibition.service.dto.ViewDTO;
import com.artbridge.exhibition.service.mapper.ViewMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link ViewResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewResourceIT {

    private static final Long DEFAULT_VO_MEMBER = 1L;
    private static final Long UPDATED_VO_MEMBER = 2L;

    private static final String ENTITY_API_URL = "/api/views";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ViewRepository viewRepository;

    @Autowired
    private ViewMapper viewMapper;

    @Autowired
    private MockMvc restViewMockMvc;

    private View view;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static View createEntity() {
        View view = new View().voMember(DEFAULT_VO_MEMBER);
        return view;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static View createUpdatedEntity() {
        View view = new View().voMember(UPDATED_VO_MEMBER);
        return view;
    }

    @BeforeEach
    public void initTest() {
        viewRepository.deleteAll();
        view = createEntity();
    }

    @Test
    void createView() throws Exception {
        int databaseSizeBeforeCreate = viewRepository.findAll().size();
        // Create the View
        ViewDTO viewDTO = viewMapper.toDto(view);
        restViewMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewDTO)))
            .andExpect(status().isCreated());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeCreate + 1);
        View testView = viewList.get(viewList.size() - 1);
        assertThat(testView.getVoMember()).isEqualTo(DEFAULT_VO_MEMBER);
    }

    @Test
    void createViewWithExistingId() throws Exception {
        // Create the View with an existing ID
        view.setId("existing_id");
        ViewDTO viewDTO = viewMapper.toDto(view);

        int databaseSizeBeforeCreate = viewRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllViews() throws Exception {
        // Initialize the database
        viewRepository.save(view);

        // Get all the viewList
        restViewMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(view.getId())))
            .andExpect(jsonPath("$.[*].voMember").value(hasItem(DEFAULT_VO_MEMBER.intValue())));
    }

    @Test
    void getView() throws Exception {
        // Initialize the database
        viewRepository.save(view);

        // Get the view
        restViewMockMvc
            .perform(get(ENTITY_API_URL_ID, view.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(view.getId()))
            .andExpect(jsonPath("$.voMember").value(DEFAULT_VO_MEMBER.intValue()));
    }

    @Test
    void getNonExistingView() throws Exception {
        // Get the view
        restViewMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingView() throws Exception {
        // Initialize the database
        viewRepository.save(view);

        int databaseSizeBeforeUpdate = viewRepository.findAll().size();

        // Update the view
        View updatedView = viewRepository.findById(view.getId()).get();
        updatedView.voMember(UPDATED_VO_MEMBER);
        ViewDTO viewDTO = viewMapper.toDto(updatedView);

        restViewMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewDTO))
            )
            .andExpect(status().isOk());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
        View testView = viewList.get(viewList.size() - 1);
        assertThat(testView.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
    }

    @Test
    void putNonExistingView() throws Exception {
        int databaseSizeBeforeUpdate = viewRepository.findAll().size();
        view.setId(UUID.randomUUID().toString());

        // Create the View
        ViewDTO viewDTO = viewMapper.toDto(view);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchView() throws Exception {
        int databaseSizeBeforeUpdate = viewRepository.findAll().size();
        view.setId(UUID.randomUUID().toString());

        // Create the View
        ViewDTO viewDTO = viewMapper.toDto(view);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamView() throws Exception {
        int databaseSizeBeforeUpdate = viewRepository.findAll().size();
        view.setId(UUID.randomUUID().toString());

        // Create the View
        ViewDTO viewDTO = viewMapper.toDto(view);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(viewDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateViewWithPatch() throws Exception {
        // Initialize the database
        viewRepository.save(view);

        int databaseSizeBeforeUpdate = viewRepository.findAll().size();

        // Update the view using partial update
        View partialUpdatedView = new View();
        partialUpdatedView.setId(view.getId());

        restViewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedView.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedView))
            )
            .andExpect(status().isOk());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
        View testView = viewList.get(viewList.size() - 1);
        assertThat(testView.getVoMember()).isEqualTo(DEFAULT_VO_MEMBER);
    }

    @Test
    void fullUpdateViewWithPatch() throws Exception {
        // Initialize the database
        viewRepository.save(view);

        int databaseSizeBeforeUpdate = viewRepository.findAll().size();

        // Update the view using partial update
        View partialUpdatedView = new View();
        partialUpdatedView.setId(view.getId());

        partialUpdatedView.voMember(UPDATED_VO_MEMBER);

        restViewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedView.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedView))
            )
            .andExpect(status().isOk());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
        View testView = viewList.get(viewList.size() - 1);
        assertThat(testView.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
    }

    @Test
    void patchNonExistingView() throws Exception {
        int databaseSizeBeforeUpdate = viewRepository.findAll().size();
        view.setId(UUID.randomUUID().toString());

        // Create the View
        ViewDTO viewDTO = viewMapper.toDto(view);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchView() throws Exception {
        int databaseSizeBeforeUpdate = viewRepository.findAll().size();
        view.setId(UUID.randomUUID().toString());

        // Create the View
        ViewDTO viewDTO = viewMapper.toDto(view);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamView() throws Exception {
        int databaseSizeBeforeUpdate = viewRepository.findAll().size();
        view.setId(UUID.randomUUID().toString());

        // Create the View
        ViewDTO viewDTO = viewMapper.toDto(view);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(viewDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the View in the database
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteView() throws Exception {
        // Initialize the database
        viewRepository.save(view);

        int databaseSizeBeforeDelete = viewRepository.findAll().size();

        // Delete the view
        restViewMockMvc
            .perform(delete(ENTITY_API_URL_ID, view.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<View> viewList = viewRepository.findAll();
        assertThat(viewList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
