package com.paypal.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Payment;
import com.paypal.app.domain.PaymentDetail;
import com.paypal.app.domain.Payments;
import com.paypal.app.repository.PaymentDetailRepository;
import com.paypal.app.repository.PaymentRepository;
import com.paypal.app.service.PaymentDetailService;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {
	
	@Autowired
	PaymentDetailRepository paymentDetailRepository;
	
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public PaymentDetail savePaymentDetails(PaymentDetail paymentDetail) {
		return paymentDetailRepository.save(paymentDetail);
	}

	@Override
	public PaymentDetail getDetailsByPayment(Long paymentId) {
		Optional<Payments> payment = paymentRepository.findById(paymentId);
		return paymentDetailRepository.findByPayment(payment.get());
	}

}
