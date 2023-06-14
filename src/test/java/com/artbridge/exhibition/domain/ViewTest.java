package com.artbridge.exhibition.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.artbridge.exhibition.domain.model.View;
import com.artbridge.exhibition.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(View.class);
        View view1 = new View();
        view1.setId("id1");
        View view2 = new View();
        view2.setId(view1.getId());
        assertThat(view1).isEqualTo(view2);
        view2.setId("id2");
        assertThat(view1).isNotEqualTo(view2);
        view1.setId(null);
        assertThat(view1).isNotEqualTo(view2);
    }
}
