package com.paypal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypal.app.domain.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

}
