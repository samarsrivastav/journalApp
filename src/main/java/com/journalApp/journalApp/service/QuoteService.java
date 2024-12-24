package com.journalApp.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.journalApp.journalApp.entity.QuoteApiResponse;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class QuoteService {

    private final String API_KEY;
    String API_URI="https://api.api-ninjas.com/v1/quotes?category=CATEGORY";

    public QuoteService() {
        Dotenv dotenv = Dotenv.configure().load();
        this.API_KEY = dotenv.get("API_KEY");
    }

    @Autowired
    private RestTemplate restTemplate;

    public QuoteApiResponse getQuote(String category){
        String finalAPi=API_URI.replace("CATEGORY", category);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY); // Set API key in the header
        HttpEntity<Void> entity = new HttpEntity<>(headers);

       ResponseEntity<QuoteApiResponse[]> response= restTemplate.exchange(finalAPi, HttpMethod.GET,entity,QuoteApiResponse[].class);
        QuoteApiResponse[] body= response.getBody();
        return (body!=null && body.length>0 )?body[0]:null;
    }
 
}
