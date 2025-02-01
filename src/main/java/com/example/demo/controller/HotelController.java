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
import com.example.demo.model.Hotel;
import com.example.demo.request.HotelRequest;
import com.example.demo.service.HotelService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hotel")
public class HotelController {

        @Autowired
        private HotelService hotelService;

        @GetMapping("/{id}")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "The hotel is successfully get", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hotel.class))),
                        @ApiResponse(responseCode = "404", description = "The hotel is not found")
        })
        public Mono<ResponseEntity<Hotel>> getHotel(@PathVariable int id) {
                return this.hotelService.getHotel(id)
                                .map(country -> ResponseEntity.status(200).body(country))
                                .onErrorResume(NotFoundException.class,
                                                e -> Mono.just(ResponseEntity.status(404).build()));
        }

        @PostMapping()
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "The hotel is successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hotel.class))),
                        @ApiResponse(responseCode = "400", description = "The hotel is already created")
        })
        public Mono<ResponseEntity<Object>> addCountry(@RequestBody HotelRequest hotelRequest) {
                return this.hotelService.addHotel(hotelRequest.getHotelName(), hotelRequest.getCityId())
                                .map(country -> ResponseEntity.status(201).body(country))
                                .onErrorResume(AlreadyCreatedException.class,
                                                e -> Mono.just(ResponseEntity.status(400).build()));
        }

        @GetMapping()
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "The hotels are successfully get", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hotel.class)))
        })
        public Flux<Hotel> getAllCountries() {
                return this.hotelService.getAllHotels()
                                .map(listCountries -> listCountries);
        }
}