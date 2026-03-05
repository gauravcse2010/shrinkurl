package com.shrinkurl.shrinkurl.controller;

import com.shrinkurl.shrinkurl.dto.ShortenUrlRequest;
import com.shrinkurl.shrinkurl.dto.ShortenUrlResponse;
import com.shrinkurl.shrinkurl.service.UrlShortenerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlShortenerController {

    private final UrlShortenerService service;

    public UrlShortenerController(UrlShortenerService service){
        this.service = service;
    }
    @PostMapping("/shorten")
    public ShortenUrlResponse shorten(@RequestBody @Valid ShortenUrlRequest request) {
        String code = service.createShortUrl(request.getLongUrl());
        String shortUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{code}")
                .buildAndExpand(code)
                .toUriString();

        return new ShortenUrlResponse(shortUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String longUrl = service.getLongUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }

}

