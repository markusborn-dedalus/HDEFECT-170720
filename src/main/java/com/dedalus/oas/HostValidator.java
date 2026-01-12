package com.dedalus.oas;

import java.util.regex.Pattern;

public class HostValidator {

    private static final Pattern HOST_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?)*(:\\d{1,5})?$"
    );

    public void validate(String host) throws InvalidHostException {
        if (host == null || host.isEmpty()) {
            throw new InvalidHostException("The host must not be null or empty.");
        }
        if (!HOST_PATTERN.matcher(host).matches()) {
            throw new InvalidHostException(host + " is not a valid hostname. The hostname must consist of letters, digits, hyphens, and dots, and may include an optional port number." +
                    " Examples of valid hostnames: example.com, sub.domain.org, localhost, ws000824.dedalus.lan:8443");
        }
    }
}
