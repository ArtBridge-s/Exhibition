package com.artbridge.exhibition.domain.service;

import com.artbridge.exhibition.domain.exception.CurrentTokenNotAvailableException;
import com.artbridge.exhibition.domain.model.Comment;
import com.artbridge.exhibition.infrastructure.security.SecurityUtils;
import com.artbridge.exhibition.infrastructure.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentDomainServiceImpl implements CommentDomainService {

    private final TokenProvider tokenProvider;


    @Override
    public Comment initCreatedMember(Comment comment) {
        Optional<String> optToken = SecurityUtils.getCurrentUserJWT();

        if (optToken.isEmpty() || !this.tokenProvider.validateToken(optToken.get())) {
            throw new CurrentTokenNotAvailableException("Invalid token", "Exhibition", "invalidtoken");
        }

        Authentication authentication = this.tokenProvider.getAuthentication(optToken.get());
        String login = authentication.getName();

        Long id = this.tokenProvider.getUserIdFromToken(optToken.get());

        comment.setCreatedMember(id, login);

        return comment;
    }
}
