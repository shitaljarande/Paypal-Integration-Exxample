package com.paypal.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.paypal.app.domain.PaymentDetail;


public class PaymentDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

    private String paymentId;
    
    private String paymentMode;
    
    private String payerName;
    
    private String payerEmail;
    
    private String payerId;
    
    private String payerShippingAddress;
    
    private ZonedDateTime paymentDateTime;
    
    private String paymentState;
    
    private String amount;
    
    private String currency;
    
    private String paymentType;

	public PaymentDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDetailsDTO(PaymentDetail paymentDetail) {
		super();
		this.paymentId = paymentDetail.getPaymentId();
		this.paymentMode = paymentDetail.getPaymentMode();
		this.payerName = paymentDetail.getPayerName();
		this.payerEmail = paymentDetail.getPayerEmail();
		this.payerId = paymentDetail.getPayerId();
		this.payerShippingAddress = paymentDetail.getPayerShippingAddress();
		this.paymentDateTime = paymentDetail.getPaymentDateTime();
		this.paymentState = paymentDetail.getPaymentState();
		this.amount = paymentDetail.getPayment().getPaymentAmount().toString();
		this.currency = paymentDetail.getPayment().getCurrency();
		this.paymentType = paymentDetail.getPayment().getPaymentMethod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayerShippingAddress() {
		return payerShippingAddress;
	}

	public void setPayerShippingAddress(String payerShippingAddress) {
		this.payerShippingAddress = payerShippingAddress;
	}

	public ZonedDateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(ZonedDateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
