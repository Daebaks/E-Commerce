package com.revature.security.jwt;

import com.revature.exception.BlankAuthorizationHeaderException;
import com.revature.model.type.ErrorResponseStatusType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JwtHeaderTokenExtractor {

    private static final String HEADER_PREFIX = "Bearer ";

    public static String extract(String header) {
        log.debug("[extract] Attempting to extract token");
        if (StringUtils.isBlank(header)) {
            log.error("[extract] Authorization header cannot be blank");
            throw new BlankAuthorizationHeaderException(ErrorResponseStatusType.BLANK_AUTHORIZATION_HEADER_EXCEPTION);
        }
        if (!StringUtils.startsWith(header, HEADER_PREFIX)) {
            log.error("[extract] Header must starts with 'Bearer' prefix");
            throw new BlankAuthorizationHeaderException(ErrorResponseStatusType.MALFORMED_AUTHORIZATION_HEADER_EXCEPTION);
        }
        if (header.length() < HEADER_PREFIX.length()) {
            log.error("[extract] Invalid authorization header size");
            throw new BlankAuthorizationHeaderException(ErrorResponseStatusType.MALFORMED_AUTHORIZATION_HEADER_EXCEPTION);
        }
        return header.substring(HEADER_PREFIX.length());
    }
}
