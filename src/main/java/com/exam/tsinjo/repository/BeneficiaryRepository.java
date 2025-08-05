package com.exam.tsinjo.repository;

import com.exam.tsinjo.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, String> {
  // Les méthodes CRUD de base sont déjà disponibles.
}
