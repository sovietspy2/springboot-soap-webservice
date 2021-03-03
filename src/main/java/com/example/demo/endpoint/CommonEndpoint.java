package com.example.demo.endpoint;

import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CommonEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    @Autowired
    public CommonEndpoint(CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCityRequest")
    @ResponsePayload
    public GetCityResponse getCityRequest(@RequestPayload GetCityRequest request) {
        GetCityResponse response = new GetCityResponse();
        response.setCity(cityRepository.findCityByName(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCityRequest")
    @ResponsePayload
    public AddCityResponse addCityRequest(@RequestPayload AddCityRequest request) {
        AddCityResponse response = new AddCityResponse();
        cityRepository.addCity(request.getCity());
        response.setName("Success");
        return response;
    }

}
