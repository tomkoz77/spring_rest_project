package com.example.simplelibrary.core.checkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepo extends JpaRepository<Checkout, Long> {
}
