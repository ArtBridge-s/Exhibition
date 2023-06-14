package com.artbridge.exhibition.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.artbridge.exhibition.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExhibitionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Exhibition.class);
        Exhibition exhibition1 = new Exhibition();
        exhibition1.setId("id1");
        Exhibition exhibition2 = new Exhibition();
        exhibition2.setId(exhibition1.getId());
        assertThat(exhibition1).isEqualTo(exhibition2);
        exhibition2.setId("id2");
        assertThat(exhibition1).isNotEqualTo(exhibition2);
        exhibition1.setId(null);
        assertThat(exhibition1).isNotEqualTo(exhibition2);
    }
}
