package com.paypal.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payment_details")
public class PaymentDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paypal_pay_id")
    private String paymentId;
    
    @Column(name = "payment_mode")
    private String paymentMode;
    
    @Column(name = "payer_name")
    private String payerName;
    
    @Column(name = "payer_email")
    private String payerEmail;
    
    @Column(name = "payer_id")
    private String payerId;
    
    @Size(max = 750)
    @Column(name = "payer_shipping_address")
    private String payerShippingAddress;
    
    @Column(name = "payment_date_time")
    private ZonedDateTime paymentDateTime;
    
    @Column(name = "payment_state")
    private String paymentState;
    
    @ManyToOne
    @JsonIgnore
    private Payments payment;

	public PaymentDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDetail(String paymentId, String paymentMode, String payerName, String payerEmail,
			String payerId, String payerShippingAddress, ZonedDateTime paymentDateTime, Payments payment,
			String paymentState) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.payerName = payerName;
		this.payerEmail = payerEmail;
		this.payerId = payerId;
		this.payerShippingAddress = payerShippingAddress;
		this.paymentDateTime = paymentDateTime;
		this.payment = payment;
		this.paymentState = paymentState;
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

	public Payments getPayment() {
		return payment;
	}

	public void setPayment(Payments payment) {
		this.payment = payment;
	}

	public String getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	@Override
	public String toString() {
		return "PaymentDetail [id=" + id + ", paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", payerName="
				+ payerName + ", payerEmail=" + payerEmail + ", payerId=" + payerId + ", payerShippingAddress="
				+ payerShippingAddress + ", paymentDateTime=" + paymentDateTime + ", paymentState=" + paymentState
				+ ", payment=" + payment + "]";
	}    

}
