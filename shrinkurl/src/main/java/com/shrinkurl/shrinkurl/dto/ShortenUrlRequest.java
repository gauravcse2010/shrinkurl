package com.shrinkurl.shrinkurl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortenUrlRequest {

    @NotBlank
    private String longUrl;

}
