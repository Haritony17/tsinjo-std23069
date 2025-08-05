package com.exam.tsinjo.external;

import com.exam.tsinjo.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VolaApiClient {
  private static final String PSP_TYPE = "ORANGE_MONEY"; // Valeur constante

  private final RestTemplate restTemplate;
  private final String apiKey;
  private final String baseUrl;

  public VolaApiClient(
      RestTemplate restTemplate,
      @Value("${KEY_API}") String apiKey, // Changé de KEY_API à vola.api.key
      @Value("${URL_KEY}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.apiKey = apiKey;
    this.baseUrl = baseUrl;
  }

  public Payment getPaymentStatus(String pspPaymentId, String payerEmail) {
    String url = buildPaymentUrl(pspPaymentId, payerEmail);

    HttpEntity<String> entity = new HttpEntity<>(buildHeaders());

    ResponseEntity<Payment> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, Payment.class);

    return processResponse(response, "GET");
  }

  public Payment createPayment(String pspPaymentId, String payerEmail) {
    String url = buildPaymentUrl(pspPaymentId, payerEmail);

    HttpEntity<String> entity = new HttpEntity<>(buildHeaders());

    ResponseEntity<Payment> response =
        restTemplate.exchange(url, HttpMethod.POST, entity, Payment.class);

    return processResponse(response, "POST");
  }

  public String ping() {
    return restTemplate.getForObject(baseUrl + "/ping", String.class);
  }

  private String buildPaymentUrl(String pspPaymentId, String payerEmail) {
    return String.format(
        "%s/payment?apiKey=%s&payerEmail=%s&pspType=%s&pspPaymentId=%s",
        baseUrl, apiKey, payerEmail, PSP_TYPE, pspPaymentId);
  }

  private HttpHeaders buildHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-API-KEY", apiKey);
    return headers;
  }

  private Payment processResponse(ResponseEntity<Payment> response, String method) {
    if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
      return response.getBody();
    }
    throw new RuntimeException(
        "Vola API " + method + " request failed with status: " + response.getStatusCode());
  }
}
