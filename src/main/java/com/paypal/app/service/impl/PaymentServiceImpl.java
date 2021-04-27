package com.paypal.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.app.domain.Payments;
import com.paypal.app.repository.PaymentRepository;
import com.paypal.app.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payments savePayment(Payments payment) {
		// TODO Auto-generated method stub
		return paymentRepository.save(payment);
	}

}
