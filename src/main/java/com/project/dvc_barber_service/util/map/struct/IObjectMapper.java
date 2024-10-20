package com.project.dvc_barber_service.util.map.struct;

public interface IObjectMapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);
}
