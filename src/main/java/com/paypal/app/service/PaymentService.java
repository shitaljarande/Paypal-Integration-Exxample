package com.paypal.app.service;

import com.paypal.app.domain.Payments;

public interface PaymentService {
	
	Payments savePayment(Payments payment);

}
