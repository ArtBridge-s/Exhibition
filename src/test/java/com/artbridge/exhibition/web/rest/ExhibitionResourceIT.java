package com.artbridge.exhibition.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.artbridge.exhibition.IntegrationTest;
import com.artbridge.exhibition.domain.model.Exhibition;
import com.artbridge.exhibition.domain.enumeration.Status;
import com.artbridge.exhibition.repository.ExhibitionRepository;
import com.artbridge.exhibition.service.dto.ExhibitionDTO;
import com.artbridge.exhibition.service.mapper.ExhibitionMapper;
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
 * Integration tests for the {@link ExhibitionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExhibitionResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_FEE = "AAAAAAAAAA";
    private static final String UPDATED_FEE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_IMG_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMG_URL = "BBBBBBBBBB";

    private static final String DEFAULT_VO_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_VO_PERIOD = "BBBBBBBBBB";

    private static final String DEFAULT_VO_ARTIST = "AAAAAAAAAA";
    private static final String UPDATED_VO_ARTIST = "BBBBBBBBBB";

    private static final String DEFAULT_VO_MEMBER = "AAAAAAAAAA";
    private static final String UPDATED_VO_MEMBER = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.UPLOAD_PENDING;
    private static final Status UPDATED_STATUS = Status.REVISION_PENDING;

    private static final String ENTITY_API_URL = "/api/exhibitions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Autowired
    private ExhibitionMapper exhibitionMapper;

    @Autowired
    private MockMvc restExhibitionMockMvc;

    private Exhibition exhibition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Exhibition createEntity() {
        Exhibition exhibition = new Exhibition()
            .title(DEFAULT_TITLE)
            .location(DEFAULT_LOCATION)
            .fee(DEFAULT_FEE)
            .contact(DEFAULT_CONTACT)
            .imgUrl(DEFAULT_IMG_URL)
            .voPeriod(DEFAULT_VO_PERIOD)
            .voArtist(DEFAULT_VO_ARTIST)
            .voMember(DEFAULT_VO_MEMBER)
            .status(DEFAULT_STATUS);
        return exhibition;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Exhibition createUpdatedEntity() {
        Exhibition exhibition = new Exhibition()
            .title(UPDATED_TITLE)
            .location(UPDATED_LOCATION)
            .fee(UPDATED_FEE)
            .contact(UPDATED_CONTACT)
            .imgUrl(UPDATED_IMG_URL)
            .voPeriod(UPDATED_VO_PERIOD)
            .voArtist(UPDATED_VO_ARTIST)
            .voMember(UPDATED_VO_MEMBER)
            .status(UPDATED_STATUS);
        return exhibition;
    }

    @BeforeEach
    public void initTest() {
        exhibitionRepository.deleteAll();
        exhibition = createEntity();
    }

    @Test
    void createExhibition() throws Exception {
        int databaseSizeBeforeCreate = exhibitionRepository.findAll().size();
        // Create the Exhibition
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);
        restExhibitionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(exhibitionDTO)))
            .andExpect(status().isCreated());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeCreate + 1);
        Exhibition testExhibition = exhibitionList.get(exhibitionList.size() - 1);
        assertThat(testExhibition.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testExhibition.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testExhibition.getFee()).isEqualTo(DEFAULT_FEE);
        assertThat(testExhibition.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testExhibition.getImgUrl()).isEqualTo(DEFAULT_IMG_URL);
        assertThat(testExhibition.getVoPeriod()).isEqualTo(DEFAULT_VO_PERIOD);
        assertThat(testExhibition.getVoArtist()).isEqualTo(DEFAULT_VO_ARTIST);
        assertThat(testExhibition.getVoMember()).isEqualTo(DEFAULT_VO_MEMBER);
        assertThat(testExhibition.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void createExhibitionWithExistingId() throws Exception {
        // Create the Exhibition with an existing ID
        exhibition.setId("existing_id");
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);

        int databaseSizeBeforeCreate = exhibitionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExhibitionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(exhibitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllExhibitions() throws Exception {
        // Initialize the database
        exhibitionRepository.save(exhibition);

        // Get all the exhibitionList
        restExhibitionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exhibition.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].fee").value(hasItem(DEFAULT_FEE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].imgUrl").value(hasItem(DEFAULT_IMG_URL)))
            .andExpect(jsonPath("$.[*].voPeriod").value(hasItem(DEFAULT_VO_PERIOD)))
            .andExpect(jsonPath("$.[*].voArtist").value(hasItem(DEFAULT_VO_ARTIST)))
            .andExpect(jsonPath("$.[*].voMember").value(hasItem(DEFAULT_VO_MEMBER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    void getExhibition() throws Exception {
        // Initialize the database
        exhibitionRepository.save(exhibition);

        // Get the exhibition
        restExhibitionMockMvc
            .perform(get(ENTITY_API_URL_ID, exhibition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(exhibition.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.fee").value(DEFAULT_FEE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.imgUrl").value(DEFAULT_IMG_URL))
            .andExpect(jsonPath("$.voPeriod").value(DEFAULT_VO_PERIOD))
            .andExpect(jsonPath("$.voArtist").value(DEFAULT_VO_ARTIST))
            .andExpect(jsonPath("$.voMember").value(DEFAULT_VO_MEMBER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    void getNonExistingExhibition() throws Exception {
        // Get the exhibition
        restExhibitionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingExhibition() throws Exception {
        // Initialize the database
        exhibitionRepository.save(exhibition);

        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();

        // Update the exhibition
        Exhibition updatedExhibition = exhibitionRepository.findById(exhibition.getId()).get();
        updatedExhibition
            .title(UPDATED_TITLE)
            .location(UPDATED_LOCATION)
            .fee(UPDATED_FEE)
            .contact(UPDATED_CONTACT)
            .imgUrl(UPDATED_IMG_URL)
            .voPeriod(UPDATED_VO_PERIOD)
            .voArtist(UPDATED_VO_ARTIST)
            .voMember(UPDATED_VO_MEMBER)
            .status(UPDATED_STATUS);
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(updatedExhibition);

        restExhibitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exhibitionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exhibitionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
        Exhibition testExhibition = exhibitionList.get(exhibitionList.size() - 1);
        assertThat(testExhibition.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testExhibition.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testExhibition.getFee()).isEqualTo(UPDATED_FEE);
        assertThat(testExhibition.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testExhibition.getImgUrl()).isEqualTo(UPDATED_IMG_URL);
        assertThat(testExhibition.getVoPeriod()).isEqualTo(UPDATED_VO_PERIOD);
        assertThat(testExhibition.getVoArtist()).isEqualTo(UPDATED_VO_ARTIST);
        assertThat(testExhibition.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
        assertThat(testExhibition.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void putNonExistingExhibition() throws Exception {
        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();
        exhibition.setId(UUID.randomUUID().toString());

        // Create the Exhibition
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExhibitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exhibitionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exhibitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchExhibition() throws Exception {
        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();
        exhibition.setId(UUID.randomUUID().toString());

        // Create the Exhibition
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExhibitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(exhibitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamExhibition() throws Exception {
        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();
        exhibition.setId(UUID.randomUUID().toString());

        // Create the Exhibition
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExhibitionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(exhibitionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateExhibitionWithPatch() throws Exception {
        // Initialize the database
        exhibitionRepository.save(exhibition);

        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();

        // Update the exhibition using partial update
        Exhibition partialUpdatedExhibition = new Exhibition();
        partialUpdatedExhibition.setId(exhibition.getId());

        partialUpdatedExhibition.fee(UPDATED_FEE).status(UPDATED_STATUS);

        restExhibitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExhibition.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExhibition))
            )
            .andExpect(status().isOk());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
        Exhibition testExhibition = exhibitionList.get(exhibitionList.size() - 1);
        assertThat(testExhibition.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testExhibition.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testExhibition.getFee()).isEqualTo(UPDATED_FEE);
        assertThat(testExhibition.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testExhibition.getImgUrl()).isEqualTo(DEFAULT_IMG_URL);
        assertThat(testExhibition.getVoPeriod()).isEqualTo(DEFAULT_VO_PERIOD);
        assertThat(testExhibition.getVoArtist()).isEqualTo(DEFAULT_VO_ARTIST);
        assertThat(testExhibition.getVoMember()).isEqualTo(DEFAULT_VO_MEMBER);
        assertThat(testExhibition.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void fullUpdateExhibitionWithPatch() throws Exception {
        // Initialize the database
        exhibitionRepository.save(exhibition);

        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();

        // Update the exhibition using partial update
        Exhibition partialUpdatedExhibition = new Exhibition();
        partialUpdatedExhibition.setId(exhibition.getId());

        partialUpdatedExhibition
            .title(UPDATED_TITLE)
            .location(UPDATED_LOCATION)
            .fee(UPDATED_FEE)
            .contact(UPDATED_CONTACT)
            .imgUrl(UPDATED_IMG_URL)
            .voPeriod(UPDATED_VO_PERIOD)
            .voArtist(UPDATED_VO_ARTIST)
            .voMember(UPDATED_VO_MEMBER)
            .status(UPDATED_STATUS);

        restExhibitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExhibition.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExhibition))
            )
            .andExpect(status().isOk());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
        Exhibition testExhibition = exhibitionList.get(exhibitionList.size() - 1);
        assertThat(testExhibition.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testExhibition.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testExhibition.getFee()).isEqualTo(UPDATED_FEE);
        assertThat(testExhibition.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testExhibition.getImgUrl()).isEqualTo(UPDATED_IMG_URL);
        assertThat(testExhibition.getVoPeriod()).isEqualTo(UPDATED_VO_PERIOD);
        assertThat(testExhibition.getVoArtist()).isEqualTo(UPDATED_VO_ARTIST);
        assertThat(testExhibition.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
        assertThat(testExhibition.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void patchNonExistingExhibition() throws Exception {
        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();
        exhibition.setId(UUID.randomUUID().toString());

        // Create the Exhibition
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExhibitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, exhibitionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exhibitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchExhibition() throws Exception {
        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();
        exhibition.setId(UUID.randomUUID().toString());

        // Create the Exhibition
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExhibitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(exhibitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamExhibition() throws Exception {
        int databaseSizeBeforeUpdate = exhibitionRepository.findAll().size();
        exhibition.setId(UUID.randomUUID().toString());

        // Create the Exhibition
        ExhibitionDTO exhibitionDTO = exhibitionMapper.toDto(exhibition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExhibitionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(exhibitionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Exhibition in the database
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteExhibition() throws Exception {
        // Initialize the database
        exhibitionRepository.save(exhibition);

        int databaseSizeBeforeDelete = exhibitionRepository.findAll().size();

        // Delete the exhibition
        restExhibitionMockMvc
            .perform(delete(ENTITY_API_URL_ID, exhibition.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Exhibition> exhibitionList = exhibitionRepository.findAll();
        assertThat(exhibitionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
