package org.wheelsshop.mapper;

public interface MapperContract<D, T, R> {
    D toDto(T t);

    T fromRequest(R r);

    T fromDto(D d);
}
