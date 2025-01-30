package com.example.demo.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;

import reactor.core.publisher.Mono;

@WebFluxTest(CountryController.class)
public class CountryControllerTest {

    @InjectMocks
    CountryController countryController;

    @MockBean
    private CountryService countryService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetCountrySuccess() {
        when(countryService.getCountry(1)).thenReturn(Mono.just(new Country("France")));

        webTestClient.get()
                .uri("/country/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Country.class)
                .isEqualTo(new Country("France"));
    }
}