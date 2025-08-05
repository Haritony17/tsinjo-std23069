package com.exam.tsinjo.service;

import com.exam.tsinjo.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VolaService {
  @Value("${KEY_API}")
  private String volaApiKey;

  private final RestTemplate restTemplate = new RestTemplate();

  public Payment verifyPayment(String paymentId) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("X-API-KEY", volaApiKey);
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<Payment> response =
        restTemplate.exchange(
            "https://vola-api.example.com/payments/" + paymentId,
            HttpMethod.GET,
            entity,
            Payment.class);

    return response.getBody();
  }
}
