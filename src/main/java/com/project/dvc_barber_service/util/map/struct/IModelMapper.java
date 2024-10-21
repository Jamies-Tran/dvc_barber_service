package com.project.dvc_barber_service.util.map.struct;

public interface IModelMapper<M, D> {
    M toModel(D d);

    D toDto(M model);
}
