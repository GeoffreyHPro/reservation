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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The country is successfully get", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))),
            @ApiResponse(responseCode = "404", description = "The country is not found", content = @Content(mediaType = "application/json"))
    })
    public Mono<ResponseEntity<Country>> getCity(@PathVariable int id) {
        return this.countryService.getCountry(id)
                .map(country -> ResponseEntity.status(200).body(country))
                .onErrorResume(NotFoundException.class, e -> Mono.just(ResponseEntity.status(404).build()));
    }
}
