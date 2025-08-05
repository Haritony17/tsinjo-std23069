package com.exam.tsinjo.repository;

import com.exam.tsinjo.model.Donor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, String> {
  Optional<Donor> findByEmail(String donorEmail);
  // Les méthodes CRUD de base (find, save, delete, etc.) sont héritées de JpaRepository.
  // Vous n'avez pas besoin d'ajouter de méthodes ici pour l'instant.
}
