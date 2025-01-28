package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @Test
    void testAddCountryAndFindSuccess() {
        Country savedCountry = new Country("France");
        savedCountry.setId(1);

        Mockito.when(countryRepository.findByCountryName("France")).thenReturn(Mono.empty());

        Mockito.when(countryRepository.save(Mockito.any(Country.class))).thenReturn(Mono.just(savedCountry));

        StepVerifier.create(countryService.addCountry("France"))
                .assertNext(saved -> {
                    Country countryFound = (Country) saved;
                    assertEquals("France", countryFound.getCountryName());
                    assertEquals(savedCountry.getId(), countryFound.getId());
                }).verifyComplete();
    }
}
