package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Country>> getCity(@PathVariable int id) {
        return this.countryService.getCountry(id)
                .map(country -> ResponseEntity.status(200).body(country))
                .onErrorResume(NotFoundException.class, e -> Mono.just(ResponseEntity.status(404).build()));
    }
}
