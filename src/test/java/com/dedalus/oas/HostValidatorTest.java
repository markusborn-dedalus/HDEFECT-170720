package com.dedalus.oas;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HostValidatorTest {

    private final HostValidator hostValidator = new HostValidator();

    @ParameterizedTest
    @ValueSource(strings = {
            "example.com",
            "sub.domain.co.uk",
            "my-site123.net",
            "api.service.io",
            "example.com:80",
            "example.com:443",
            "sub.domain.org:8080",
            "deep.sub.domain.example:12345",
            "localhost:8080",
            "ws000824.dedalus.lan"})
    void validate_returnsSilently_whenHostIsValid(String host) throws InvalidHostException {
        // when
        hostValidator.validate(host);
        // then (no execption is thrown)
    }


    @ParameterizedTest
    @NullAndEmptySource
    void validate_returnsThrowsException_whenHostIsNullOrEmpty(String host) {
        // when + then
        assertThrows(InvalidHostException.class, () -> hostValidator.validate(host));
    }

    @ParameterizedTest
    @ValueSource(strings = {
//            "-example.com",
            "example-.com",
//            "exa_mple.com",
//            "example..com",
//            "example.com:999999",
//            "example.com:abc",
//            "http://example.com"
    })
    void validate_returnsThrowsException_whenHostIsInvalid(String host) {
        // when + then
        assertThrows(InvalidHostException.class, () -> hostValidator.validate(host));
    }

}
