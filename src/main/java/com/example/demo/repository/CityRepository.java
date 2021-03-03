package com.example.demo.repository;

import io.spring.guides.gs_producing_web_service.City;
import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import io.spring.guides.gs_producing_web_service.Language;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CityRepository {

    private List<City> cities = new ArrayList<>();

    @PostConstruct
    private void init() {
        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        Language lang1 = new Language();
        lang1.setName("Hungarian");
        lang1.setCode("HUN");

        Language lang2 = new Language();
        lang2.setCode("ENG");
        lang2.setName("English");

        City city = new City();
        city.setName("London");
        city.setCountry(uk);
        city.setSize(BigInteger.valueOf(1));

        city.getLanguages().add(lang1);
        city.getLanguages().add(lang2);

        this.cities.add(city);
    }

    public City findCityByName(String name) {
        Optional<City> city = cities.stream().filter(c->c.getName().equals(name)).findFirst();
        return city.orElseGet(City::new);
    }

    public void addCity(City city) {
        cities.add(city);
    }


}
