package com.example.ordermanagement.xport;

import com.example.ordermanagement.domain.valueobjects.Pizza;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

@Service
public class PizzaClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public PizzaClient(@Value ("${app.pizza-catalog.url}") String serverUrl) {
        this.restTemplate = new RestTemplate();
        this.serverUrl = serverUrl;
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri(){
        return UriComponentsBuilder.fromUriString((this.serverUrl));
    }

    public List<Pizza> findAll(){
        try{
            return restTemplate.exchange(uri().path("/api/pizza").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Pizza>>(){
                    }).getBody();
        } catch(Exception e){
            return Collections.emptyList();
        }
    }
}
