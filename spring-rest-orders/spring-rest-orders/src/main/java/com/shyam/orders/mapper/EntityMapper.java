package com.shyam.orders.mapper;

public interface EntityMapper <D, E>{
    E toEntity(D dto) throws Exception;
}
