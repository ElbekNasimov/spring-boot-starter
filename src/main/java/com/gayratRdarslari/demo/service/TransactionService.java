package com.gayratRdarslari.demo.service;

import com.gayratRdarslari.demo.model.Transaction;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

    private final String URI = "http://localhost:8083/api/transactions";
    public String getMsg(){
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(URI + "/test", String.class);
        return msg;
    }

    public Transaction save(Transaction transaction){
        RestTemplate restTemplate = new RestTemplate();
        Transaction transaction1 = restTemplate.postForObject(URI, transaction, Transaction.class);
        return transaction1;
    }

    public List<Transaction> getAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Transaction>> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(URI + "/test", HttpMethod.GET, entity, List.class).getBody();
    }

    public Transaction saveExchange(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(URI, HttpMethod.POST, entity, Transaction.class).getBody();
    }

    public Transaction update(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(URI, HttpMethod.PUT, entity, Transaction.class).getBody();
    }

}
