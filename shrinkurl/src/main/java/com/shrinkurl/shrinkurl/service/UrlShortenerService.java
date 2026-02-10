package com.shrinkurl.shrinkurl.service;

import com.shrinkurl.shrinkurl.entity.UrlMapping;
import com.shrinkurl.shrinkurl.exception.UrlNotFoundException;
import com.shrinkurl.shrinkurl.repository.UrlMappingRepository;
import com.shrinkurl.shrinkurl.util.Base62Encoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository repository;
    private final Base62Encoder encoder;

    public UrlShortenerService(UrlMappingRepository repository,
                               Base62Encoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public String createShortUrl(String longUrl) {
        UrlMapping mapping = new UrlMapping();
        mapping.setLongUrl(longUrl);
        mapping.setCreatedAt(LocalDateTime.now());

        mapping = repository.save(mapping); // get ID

        String shortCode = encoder.encode(mapping.getId());
        mapping.setShortCode(shortCode);

        repository.save(mapping);
        return shortCode;
    }

    public String getLongUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(UrlMapping::getLongUrl)
                .orElseThrow(() -> new UrlNotFoundException("Invalid short URL"));
    }

}

