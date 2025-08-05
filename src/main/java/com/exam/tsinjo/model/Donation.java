package com.exam.tsinjo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Donation.java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
  @Id private UUID id;
  private String donorName;
  private String donorEmail;
  private double amount;
  private String pspPaymentId; // Ajouté pour stocker l'ID du paiement Vola
  private String status; // VERIFYING, SUCCEEDED, FAILED
  private LocalDateTime creationDatetime;
  private UUID donorId;
  private UUID paymentId;

  public void setDonorId(UUID donorId) {
    this.donorId = donorId;
  }

  public UUID getDonorId() {
    return donorId;
  }

  public void setPaymentId(UUID paymentId) {
    this.paymentId = paymentId;
  }

  public UUID getPaymentId() {
    return paymentId;
  }
}
