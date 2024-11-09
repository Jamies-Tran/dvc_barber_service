package com.project.dvc_barber_service.util.object.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dvc_barber_service.dto.media.Media;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AppObjectMapper {
    public static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static byte[] convertDataToByte(Object data) {
        try {
            return objectMapper().writeValueAsBytes(data);
        } catch (Exception e) {
            log.error("[{}-convertDataToByte] có lỗi xảy ra: {} ", AppObjectMapper.class.getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }
    }

    public static JsonNode getJsonNode(byte[] data) {
        try {
            return objectMapper().readTree(data);
        } catch (Exception e) {
            log.error("[{}-getJsonNode] có lỗi xảy ra: {} ", AppObjectMapper.class.getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }
    }

    public static String convertDataToJsonString(Object data) {
        try {
            return objectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("[{}-convertDataToJsonString] có lỗi xảy ra: {} ", AppObjectMapper.class.getSimpleName(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static List<Media> convertImageList(byte[] images) {
        return AppObjectMapper
                .getJsonNode(images).findValuesAsText("url")
                .stream()
                .map(Media::buildFrom)
                .toList();
    }
}
