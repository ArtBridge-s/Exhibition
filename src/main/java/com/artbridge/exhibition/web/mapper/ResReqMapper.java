package com.artbridge.exhibition.web.mapper;

public interface ResReqMapper<D, R> {
    R toRes(D dto);
    R toReq(D dto);

    D toDto(R resreq);
}
