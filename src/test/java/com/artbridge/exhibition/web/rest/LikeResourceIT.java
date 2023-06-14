package com.artbridge.exhibition.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.artbridge.exhibition.IntegrationTest;
import com.artbridge.exhibition.domain.Like;
import com.artbridge.exhibition.repository.LikeRepository;
import com.artbridge.exhibition.service.dto.LikeDTO;
import com.artbridge.exhibition.service.mapper.LikeMapper;
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
 * Integration tests for the {@link LikeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LikeResourceIT {

    private static final Long DEFAULT_VO_MEMBER = 1L;
    private static final Long UPDATED_VO_MEMBER = 2L;

    private static final String ENTITY_API_URL = "/api/likes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private MockMvc restLikeMockMvc;

    private Like like;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Like createEntity() {
        Like like = new Like().voMember(DEFAULT_VO_MEMBER);
        return like;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Like createUpdatedEntity() {
        Like like = new Like().voMember(UPDATED_VO_MEMBER);
        return like;
    }

    @BeforeEach
    public void initTest() {
        likeRepository.deleteAll();
        like = createEntity();
    }

    @Test
    void createLike() throws Exception {
        int databaseSizeBeforeCreate = likeRepository.findAll().size();
        // Create the Like
        LikeDTO likeDTO = likeMapper.toDto(like);
        restLikeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(likeDTO)))
            .andExpect(status().isCreated());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeCreate + 1);
        Like testLike = likeList.get(likeList.size() - 1);
        assertThat(testLike.getVoMember()).isEqualTo(DEFAULT_VO_MEMBER);
    }

    @Test
    void createLikeWithExistingId() throws Exception {
        // Create the Like with an existing ID
        like.setId("existing_id");
        LikeDTO likeDTO = likeMapper.toDto(like);

        int databaseSizeBeforeCreate = likeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLikeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(likeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllLikes() throws Exception {
        // Initialize the database
        likeRepository.save(like);

        // Get all the likeList
        restLikeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(like.getId())))
            .andExpect(jsonPath("$.[*].voMember").value(hasItem(DEFAULT_VO_MEMBER.intValue())));
    }

    @Test
    void getLike() throws Exception {
        // Initialize the database
        likeRepository.save(like);

        // Get the like
        restLikeMockMvc
            .perform(get(ENTITY_API_URL_ID, like.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(like.getId()))
            .andExpect(jsonPath("$.voMember").value(DEFAULT_VO_MEMBER.intValue()));
    }

    @Test
    void getNonExistingLike() throws Exception {
        // Get the like
        restLikeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingLike() throws Exception {
        // Initialize the database
        likeRepository.save(like);

        int databaseSizeBeforeUpdate = likeRepository.findAll().size();

        // Update the like
        Like updatedLike = likeRepository.findById(like.getId()).get();
        updatedLike.voMember(UPDATED_VO_MEMBER);
        LikeDTO likeDTO = likeMapper.toDto(updatedLike);

        restLikeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, likeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(likeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
        Like testLike = likeList.get(likeList.size() - 1);
        assertThat(testLike.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
    }

    @Test
    void putNonExistingLike() throws Exception {
        int databaseSizeBeforeUpdate = likeRepository.findAll().size();
        like.setId(UUID.randomUUID().toString());

        // Create the Like
        LikeDTO likeDTO = likeMapper.toDto(like);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLikeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, likeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(likeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLike() throws Exception {
        int databaseSizeBeforeUpdate = likeRepository.findAll().size();
        like.setId(UUID.randomUUID().toString());

        // Create the Like
        LikeDTO likeDTO = likeMapper.toDto(like);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLikeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(likeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLike() throws Exception {
        int databaseSizeBeforeUpdate = likeRepository.findAll().size();
        like.setId(UUID.randomUUID().toString());

        // Create the Like
        LikeDTO likeDTO = likeMapper.toDto(like);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLikeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(likeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLikeWithPatch() throws Exception {
        // Initialize the database
        likeRepository.save(like);

        int databaseSizeBeforeUpdate = likeRepository.findAll().size();

        // Update the like using partial update
        Like partialUpdatedLike = new Like();
        partialUpdatedLike.setId(like.getId());

        partialUpdatedLike.voMember(UPDATED_VO_MEMBER);

        restLikeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLike.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLike))
            )
            .andExpect(status().isOk());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
        Like testLike = likeList.get(likeList.size() - 1);
        assertThat(testLike.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
    }

    @Test
    void fullUpdateLikeWithPatch() throws Exception {
        // Initialize the database
        likeRepository.save(like);

        int databaseSizeBeforeUpdate = likeRepository.findAll().size();

        // Update the like using partial update
        Like partialUpdatedLike = new Like();
        partialUpdatedLike.setId(like.getId());

        partialUpdatedLike.voMember(UPDATED_VO_MEMBER);

        restLikeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLike.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLike))
            )
            .andExpect(status().isOk());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
        Like testLike = likeList.get(likeList.size() - 1);
        assertThat(testLike.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
    }

    @Test
    void patchNonExistingLike() throws Exception {
        int databaseSizeBeforeUpdate = likeRepository.findAll().size();
        like.setId(UUID.randomUUID().toString());

        // Create the Like
        LikeDTO likeDTO = likeMapper.toDto(like);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLikeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, likeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(likeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLike() throws Exception {
        int databaseSizeBeforeUpdate = likeRepository.findAll().size();
        like.setId(UUID.randomUUID().toString());

        // Create the Like
        LikeDTO likeDTO = likeMapper.toDto(like);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLikeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(likeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLike() throws Exception {
        int databaseSizeBeforeUpdate = likeRepository.findAll().size();
        like.setId(UUID.randomUUID().toString());

        // Create the Like
        LikeDTO likeDTO = likeMapper.toDto(like);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLikeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(likeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Like in the database
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLike() throws Exception {
        // Initialize the database
        likeRepository.save(like);

        int databaseSizeBeforeDelete = likeRepository.findAll().size();

        // Delete the like
        restLikeMockMvc
            .perform(delete(ENTITY_API_URL_ID, like.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Like> likeList = likeRepository.findAll();
        assertThat(likeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
