package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.request.CountryRequest;
import com.example.demo.service.CountryService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(CountryController.class)
public class CountryControllerTest {

    @InjectMocks
    CountryController countryController;

    @MockBean
    private CountryService countryService;

    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testAddCountrySuccess() {
        Country country = new Country("France");

        when(countryRepository.save(country)).thenReturn(Mono.just(country));
        when(countryService.addCountry("France")).thenReturn(Mono.just(country));

        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/country")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new CountryRequest("France")))
                .exchange();

        assertEquals(201, response.returnResult(SliceImpl.class).getStatus().value());
    }

    @Test
    public void testGetCountrySuccess() {
        Country country = new Country("France");

        when(countryRepository.findById(1)).thenReturn(Mono.just(country));
        when(countryService.getCountry(1)).thenReturn(Mono.just(country));

        webTestClient.get()
                .uri("/country/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Country.class)
                .value(countryResponse -> {
                    assertEquals("France", countryResponse.getCountryName());
                });
    }

}