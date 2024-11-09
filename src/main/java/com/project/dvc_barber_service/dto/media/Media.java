package com.project.dvc_barber_service.dto.media;

import lombok.Builder;

@Builder
public record Media(
      String url
) {
    public static Media buildFrom(String url) {
        return Media.builder()
                .url(url)
                .build();
    }
}
