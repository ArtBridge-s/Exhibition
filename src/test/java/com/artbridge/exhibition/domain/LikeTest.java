package com.artbridge.exhibition.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.artbridge.exhibition.domain.model.Like;
import com.artbridge.exhibition.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LikeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Like.class);
        Like like1 = new Like();
        like1.setId("id1");
        Like like2 = new Like();
        like2.setId(like1.getId());
        assertThat(like1).isEqualTo(like2);
        like2.setId("id2");
        assertThat(like1).isNotEqualTo(like2);
        like1.setId(null);
        assertThat(like1).isNotEqualTo(like2);
    }
}
