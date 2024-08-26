package com.syncoder.simpnotes.services;

import com.syncoder.simpnotes.domain.Image;
import com.syncoder.simpnotes.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image findById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found!"));
    }

}
