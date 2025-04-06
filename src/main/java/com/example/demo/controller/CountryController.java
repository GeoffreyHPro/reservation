package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AlreadyCreatedException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Country;
import com.example.demo.request.CountryRequest;
import com.example.demo.service.CountryService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/country")
public class CountryController {

        @Autowired
        private CountryService countryService;

        @GetMapping("/{id}")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "The country is successfully get", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))),
                        @ApiResponse(responseCode = "404", description = "The country is not found")
        })
        public Mono<ResponseEntity<Country>> getCountry(@PathVariable int id) {
                return this.countryService.getCountry(id)
                                .map(country -> ResponseEntity.status(200).body(country))
                                .onErrorResume(NotFoundException.class,
                                                e -> Mono.just(ResponseEntity.status(404).build()));
        }

        @PostMapping()
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "The country is successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))),
                        @ApiResponse(responseCode = "400", description = "The country is already created")
        })
        public Mono<ResponseEntity<Object>> addCountry(@RequestBody CountryRequest countryRequest) {
                return this.countryService.addCountry(countryRequest.getCountryName())
                                .map(country -> ResponseEntity.status(201).body(country))
                                .onErrorResume(AlreadyCreatedException.class,
                                                e -> Mono.just(ResponseEntity.status(400).build()));
        }
        
        @GetMapping()
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "The countries are successfully get", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
        })
        public Flux<Country> getCountriesByPage(
                        @RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
                return this.countryService.getCountries(name, page, size);
        }
}
