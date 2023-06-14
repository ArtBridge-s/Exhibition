package com.artbridge.exhibition.service.mapper;

import com.artbridge.exhibition.domain.valueobject.Member;
import com.artbridge.exhibition.service.dto.MemberDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDTO, Member> {}
