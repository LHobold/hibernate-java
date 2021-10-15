package com.example.hibernatejava.service;

import java.util.List;

import com.example.hibernatejava.dao.ManufacturerDao;
import com.example.hibernatejava.entity.Manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {

    @Autowired
    ManufacturerDao manufacturerDao;

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerDao.findAll();
    }

}
