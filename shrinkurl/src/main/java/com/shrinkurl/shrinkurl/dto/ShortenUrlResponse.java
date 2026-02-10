package com.shrinkurl.shrinkurl.dto;

import lombok.Getter;
import lombok.Setter;

public class ShortenUrlResponse {
    @Getter
    @Setter
    private String shortUrl;
    public ShortenUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
