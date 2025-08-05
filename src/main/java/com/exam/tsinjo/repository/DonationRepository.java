package com.exam.tsinjo.repository;

import com.exam.tsinjo.model.Donation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, String> {
  List<Donation> findAllByOrderByCreationDatetimeDesc();
}
