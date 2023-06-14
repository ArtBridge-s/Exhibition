package com.artbridge.exhibition.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.artbridge.exhibition.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExhibitionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExhibitionDTO.class);
        ExhibitionDTO exhibitionDTO1 = new ExhibitionDTO();
        exhibitionDTO1.setId("id1");
        ExhibitionDTO exhibitionDTO2 = new ExhibitionDTO();
        assertThat(exhibitionDTO1).isNotEqualTo(exhibitionDTO2);
        exhibitionDTO2.setId(exhibitionDTO1.getId());
        assertThat(exhibitionDTO1).isEqualTo(exhibitionDTO2);
        exhibitionDTO2.setId("id2");
        assertThat(exhibitionDTO1).isNotEqualTo(exhibitionDTO2);
        exhibitionDTO1.setId(null);
        assertThat(exhibitionDTO1).isNotEqualTo(exhibitionDTO2);
    }
}
