package com.artbridge.exhibition.domain.service;

import com.artbridge.exhibition.domain.model.Exhibition;
import org.springframework.web.multipart.MultipartFile;

public interface ExhibitionDomainService {
    /**
     * 지정된 전시회에 이미지 파일을 업로드합니다.
     *
     * @param file 업로드할 이미지 파일
     * @param exhibition 이미지가 속한 전시회
     * @return 업로드된 이미지가 포함된 업데이트된 전시회 객체
     */
    Exhibition uploadImage(MultipartFile file, Exhibition exhibition);

    /**
     * 전시회에 대한 생성된 회원을 초기화합니다.
     *
     * @param exhibition 회원을 초기화할 전시회
     * @return 생성된 회원이 초기화된 업데이트된 전시회 객체
     */
    Exhibition initCreatedMember(Exhibition exhibition);

}
