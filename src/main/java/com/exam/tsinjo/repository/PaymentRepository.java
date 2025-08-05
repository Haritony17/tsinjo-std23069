package com.exam.tsinjo.repository;

import com.exam.tsinjo.model.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

  /**
   * Finds all payments that are in a specific status. This is used by the scheduled task to
   * retrieve payments that need to be verified.
   *
   * @param status The status of the payments to find (e.g., VERIFYING).
   * @return A list of payments with the specified status.
   */
  List<Payment> findAllByStatus(Payment.Status status);
}
