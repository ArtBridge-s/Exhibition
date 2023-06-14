package com.artbridge.exhibition.application.mapper;

import com.artbridge.exhibition.domain.valueobject.Member;
import com.artbridge.exhibition.application.dto.MemberDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDTO, Member> {}
