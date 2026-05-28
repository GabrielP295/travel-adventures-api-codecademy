package com.gabriel.traveladventuresapicodecademy.repositories;

import com.gabriel.traveladventuresapicodecademy.entities.Adventure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdventureRepository extends CrudRepository<Adventure, Integer> {
    public List<Adventure> findByCountry(String country);
    public List<Adventure> findByState(String state);
}
