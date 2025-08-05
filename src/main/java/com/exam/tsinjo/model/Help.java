package com.exam.tsinjo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Help {
  @Id private UUID id;
  private String beneficiaryName;
  private String beneficiaryEmail;
  private double amount;
  private String accidentDescription;
  private LocalDateTime creationDatetime;
}
