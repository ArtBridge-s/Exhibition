package com.artbridge.exhibition.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.artbridge.exhibition.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewDTO.class);
        ViewDTO viewDTO1 = new ViewDTO();
        viewDTO1.setId("id1");
        ViewDTO viewDTO2 = new ViewDTO();
        assertThat(viewDTO1).isNotEqualTo(viewDTO2);
        viewDTO2.setId(viewDTO1.getId());
        assertThat(viewDTO1).isEqualTo(viewDTO2);
        viewDTO2.setId("id2");
        assertThat(viewDTO1).isNotEqualTo(viewDTO2);
        viewDTO1.setId(null);
        assertThat(viewDTO1).isNotEqualTo(viewDTO2);
    }
}
