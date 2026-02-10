package com.shrinkurl.shrinkurl.dto;

import jakarta.validation.constraints.NotBlank;
public class ShortenUrlRequest {

    @NotBlank
    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
