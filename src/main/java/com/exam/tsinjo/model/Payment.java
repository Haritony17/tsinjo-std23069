package com.exam.tsinjo.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
  @Id private UUID id;

  @ManyToOne private Donor donor;

  private String pspPaymentId;
  private String pspType;
  private int amount;
  private Instant creationInstant;
  private Instant lastPspVerificationInstant;
  private int verificationAttemptNb;

  @Enumerated(EnumType.STRING)
  private Status status;

  public enum Status {
    VERIFYING,
    SUCCEEDED,
    FAILED
  }
}
