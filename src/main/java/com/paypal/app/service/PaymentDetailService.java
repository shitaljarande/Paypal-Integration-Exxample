package com.paypal.app.service;

import com.paypal.app.domain.PaymentDetail;

public interface PaymentDetailService {
	
	PaymentDetail savePaymentDetails(PaymentDetail paymentDetail);

	PaymentDetail getDetailsByPayment(Long paymentId);

}
