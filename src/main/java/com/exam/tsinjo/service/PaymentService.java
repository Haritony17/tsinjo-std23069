package com.exam.tsinjo.service;

import com.exam.tsinjo.external.VolaApiClient;
import com.exam.tsinjo.model.*;
import com.exam.tsinjo.repository.DonationRepository;
import com.exam.tsinjo.repository.DonorRepository;
import com.exam.tsinjo.repository.PaymentRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final DonationRepository donationRepository;
  private final DonorRepository donorRepository;
  private final VolaApiClient volaApiClient;

  public PaymentService(
      PaymentRepository paymentRepository,
      DonationRepository donationRepository,
      DonorRepository donorRepository,
      VolaApiClient volaApiClient) {
    this.paymentRepository = paymentRepository;
    this.donationRepository = donationRepository;
    this.donorRepository = donorRepository;
    this.volaApiClient = volaApiClient;
  }

  public void createDonation(String donorName, String donorEmail, String pspPaymentId, int amount) {
    // Recherche du donateur par email
    Optional<Donor> existingDonor = donorRepository.findByEmail(donorEmail);
    Donor donor =
        existingDonor.orElseGet(
            () -> {
              Donor newDonor = new Donor();
              newDonor.setId(UUID.randomUUID());
              newDonor.setFullName(donorName);
              newDonor.setEmail(donorEmail);
              return donorRepository.save(newDonor);
            });

    // Création du paiement
    Payment payment = new Payment();
    payment.setId(UUID.randomUUID());
    payment.setDonor(donor);
    payment.setPspPaymentId(pspPaymentId);
    payment.setPspType("ORANGE_MONEY");
    payment.setAmount(amount);
    payment.setCreationInstant(Instant.now());
    payment.setLastPspVerificationInstant(Instant.now());
    payment.setVerificationAttemptNb(0);
    payment.setStatus(Payment.Status.VERIFYING);
    Payment savedPayment = paymentRepository.save(payment);

    // Création du don
    Donation donation = new Donation();
    donation.setId(UUID.randomUUID());
    donation.setPaymentId(savedPayment.getId()); // Utilisation de paymentId au lieu de la relation
    donation.setDonorId(donor.getId());
    donation.setCreationDatetime(LocalDateTime.now()); // Changé à LocalDateTime
    donationRepository.save(donation);

    log.info("Nouveau don créé - ID Paiement: {}, Donateur: {}", pspPaymentId, donorEmail);
  }

  @Scheduled(fixedRate = 30000)
  public void verifyPendingPayments() {
    List<Payment> pendingPayments = paymentRepository.findAllByStatus(Payment.Status.VERIFYING);

    pendingPayments.forEach(
        payment -> {
          try {
            Payment updated =
                volaApiClient.getPaymentStatus(
                    payment.getPspPaymentId(), payment.getDonor().getEmail());

            payment.setStatus(updated.getStatus());
            payment.setLastPspVerificationInstant(Instant.now());
            payment.setVerificationAttemptNb(payment.getVerificationAttemptNb() + 1);
            paymentRepository.save(payment);

            log.info("Paiement {} mis à jour - Statut: {}", payment.getId(), payment.getStatus());
          } catch (Exception e) {
            log.error("Échec de vérification du paiement {}", payment.getId(), e.getMessage());
          }
        });
  }
}
