// TsinjoController.java (mise à jour)
package com.exam.tsinjo.endpoint.rest.controller.health;

import com.exam.tsinjo.repository.DonationRepository;
import com.exam.tsinjo.repository.HelpRepository;
import com.exam.tsinjo.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TsinjoController {

  private final DonationRepository donationRepository;
  private final HelpRepository helpRepository;
  private final PaymentService paymentService;

  public TsinjoController(
      DonationRepository donationRepository,
      HelpRepository helpRepository,
      PaymentService paymentService) {
    this.donationRepository = donationRepository;
    this.helpRepository = helpRepository;
    this.paymentService = paymentService;
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("donations", donationRepository.findAllByOrderByCreationDatetimeDesc());
    model.addAttribute("helps", helpRepository.findAllByOrderByCreationDatetimeDesc());
    return "index";
  }

  @PostMapping("/donations")
  public String createDonation(
      @RequestParam String donorName,
      @RequestParam String donorEmail,
      @RequestParam String pspPaymentId,
      @RequestParam int amount) {
    paymentService.createDonation(donorName, donorEmail, pspPaymentId, amount);
    return "redirect:/";
  }
}
