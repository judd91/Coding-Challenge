package com.example.tokenAPI.dao;
import com.example.tokenAPI.model.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends CrudRepository<Events, Integer>{
    @Override
    List<Events> findAll();
}
