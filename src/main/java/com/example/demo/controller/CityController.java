package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AlreadyCreatedException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.request.CityRequest;
import com.example.demo.service.CityService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The country is successfully get", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))),
            @ApiResponse(responseCode = "404", description = "The country is not found")
    })
    public Mono<ResponseEntity<City>> getCountry(@PathVariable int id) {
        return this.cityService.getCity(id)
                .map(country -> ResponseEntity.status(200).body(country))
                .onErrorResume(NotFoundException.class, e -> Mono.just(ResponseEntity.status(404).build()));
    }

    @PostMapping()
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "The country is successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))),
            @ApiResponse(responseCode = "400", description = "The country is already created")
    })
    public Mono<ResponseEntity<Object>> addCountry(@RequestBody CityRequest cityRequest) {
        return this.cityService.addCity(cityRequest.getCityName(), cityRequest.getCountryId())
                .map(country -> ResponseEntity.status(201).body(country))
                .onErrorResume(AlreadyCreatedException.class, e -> Mono.just(ResponseEntity.status(400).build()));
    }
}
