package com.project.dvc_barber_service.util.object.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppObjectMapper {
    public static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static byte[] convertDataToByte(Object data) {
        try {
            return objectMapper().writeValueAsBytes(data);
        } catch (Exception e) {
            log.error("[{}] có lỗi xảy ra: {} ", AppObjectMapper.class.getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }
    }

    public static JsonNode getJsonNode(byte[] data) {
        try {
            return objectMapper().readTree(data);
        } catch (Exception e) {
            log.error("[{}] có lỗi xảy ra: {} ", AppObjectMapper.class.getSimpleName(), e.getMessage());
            throw new RuntimeException();
        }
    }
}
