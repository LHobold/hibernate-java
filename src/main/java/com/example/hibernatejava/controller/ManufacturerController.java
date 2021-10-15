package com.example.hibernatejava.controller;

import java.util.List;

import com.example.hibernatejava.entity.Manufacturer;
import com.example.hibernatejava.model.Response;
import com.example.hibernatejava.service.ManufacturerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/manufacturers")
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<Response> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();

        if (manufacturers.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        Response res = new Response("SUCCESS", manufacturers);
        return ResponseEntity.ok(res);

    }
}
