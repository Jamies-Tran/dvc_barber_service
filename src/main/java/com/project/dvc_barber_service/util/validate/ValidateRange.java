package com.project.dvc_barber_service.util.validate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record ValidateRange() {
    @SuppressWarnings("unchecked")
    public static <T> List<T> validate(List<T> range) {
        if(Objects.nonNull(range)) {
            if(range.size() == 1) {
                if(range.getFirst() instanceof Long) {
                    return (List<T>) List.of(0L, range.getFirst());
                }

                if(range.getFirst() instanceof Integer) {
                    return (List<T>) List.of(0, range.getFirst());
                }

                if(range.getFirst() instanceof Double) {
                    return (List<T>) List.of(0D, range.getFirst());
                }

                if(range.getFirst() instanceof LocalDateTime) {
                    return (List<T>) List.of(LocalDateTime.now(), range.getFirst());
                }
            } else if(range.size() > 2) {
                return range.subList(0, 2).stream().sorted().toList();
            } else {
                return range.stream().sorted().toList();
            }
        }

        return range;
    }
}
