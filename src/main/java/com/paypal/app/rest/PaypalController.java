package com.paypal.app.rest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.app.domain.PaymentDetail;
import com.paypal.app.domain.Payments;
import com.paypal.app.dto.Order;
import com.paypal.app.dto.PaymentDetailsDTO;
import com.paypal.app.service.PaymentDetailService;
import com.paypal.app.service.PaymentService;
import com.paypal.app.service.PaypalService;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaypalController {

	@Autowired
	PaypalService service;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	PaymentDetailService paymentDetailService;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Order order) {
		try {
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), "http://localhost:9090/" + CANCEL_URL,
					"http://localhost:9090/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}

		} catch (PayPalRESTException e) {

			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(value = CANCEL_URL)
	public String cancelPay() {
		return "failure";
	}

	@GetMapping(value = SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model) {
		try {
			Payment payment = service.executePayment(paymentId, payerId);
			System.out.println(payment.toJSON());
			System.out.println("size :" + payment.getTransactions().size());
			Payments payInfo = new Payments(new BigDecimal(payment.getTransactions().get(0).getAmount().getTotal()), payment.getIntent(), payment.getTransactions().get(0).getAmount().getCurrency(), payment.getTransactions().get(0).getDescription(), "paypal");
			payInfo = paymentService.savePayment(payInfo);
			
			StringBuilder sbAddress = new StringBuilder();
			sbAddress.append(payment.getPayer().getPayerInfo().getShippingAddress().getLine1()).append(" ").append(payment.getPayer().getPayerInfo().getShippingAddress().getCity()).append(" ")
			.append(payment.getPayer().getPayerInfo().getShippingAddress().getPostalCode()).append(" ").append(payment.getPayer().getPayerInfo().getShippingAddress().getState());
			
			PaymentDetail paymentDetail = new PaymentDetail(payment.getId(), payment.getTransactions().get(0).getRelatedResources().get(0).getSale().getPaymentMode(), payment.getPayer().getPayerInfo().getFirstName() + " " + payment.getPayer().getPayerInfo().getLastName(),
					payment.getPayer().getPayerInfo().getEmail(), payment.getPayer().getPayerInfo().getPayerId(), sbAddress.toString(), ZonedDateTime.now(), payInfo, payment.getTransactions().get(0).getRelatedResources().get(0).getSale().getState());
			paymentDetail = paymentDetailService.savePaymentDetails(paymentDetail);			
			
			model.addAttribute("detail", paymentDetail);			
			if (payment.getState().equals("approved")) {
				return "success";
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/payment-details/{paymentId}")
	public String successPay(@PathVariable("paymentId") String paymentId, Model model) {
		PaymentDetail paymentDetail = paymentDetailService.getDetailsByPayment(Long.parseLong(paymentId));
		if (paymentDetail != null) {
			PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(paymentDetail);
			model.addAttribute("value", paymentDetailsDTO);
			return "payment-detail";
		} else {
			return "failure";
		}
		
	}


}
