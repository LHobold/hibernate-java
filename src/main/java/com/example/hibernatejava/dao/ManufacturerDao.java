package com.example.hibernatejava.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.hibernatejava.entity.Manufacturer;

import org.springframework.stereotype.Component;

@Component
public class ManufacturerDao {

    @PersistenceContext
    private EntityManager em;

    public List<Manufacturer> findAll() {
        String queryStr = String.format("SELECT m FROM Manufacturer m");
        TypedQuery<Manufacturer> q = em.createQuery(queryStr, Manufacturer.class);
        return q.getResultList();
    }

}
