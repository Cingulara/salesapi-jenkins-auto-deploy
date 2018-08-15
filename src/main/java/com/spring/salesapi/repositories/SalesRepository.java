package com.spring.salesapi.repositories;

import com.spring.salesapi.models.Sales;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SalesRepository extends CrudRepository<Sales, String> {
    @Override
    Optional<Sales> findById(String id);

    @Override
    void delete(Sales deleted);
}
