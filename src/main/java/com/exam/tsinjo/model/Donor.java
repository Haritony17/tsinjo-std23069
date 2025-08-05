package com.exam.tsinjo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Donor {
  @Id private UUID id; // L'email peut servir d'ID
  private String fullName;
  private String email;
}
