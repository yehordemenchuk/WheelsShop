package org.wheelsshop.mapper;

import org.wheelsshop.dto.Dto;

public interface EntityMapper<T> {
    Dto<T> toDto(T t);
}
