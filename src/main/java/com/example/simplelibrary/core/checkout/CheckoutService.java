package com.example.simplelibrary.core.checkout;

import com.example.simplelibrary.core.checkout.web.CheckoutBaseReq;
import com.example.simplelibrary.core.checkout.web.CheckoutView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheckoutService {

    public Checkout findCheckoutOrThrow(Long id) ;

    public CheckoutView getCheckout(Long id) ;

    public Page<CheckoutView> findAllCheckout(Pageable pageable) ;

    public CheckoutView create(CheckoutBaseReq req);

    public void delete(Long id);

    public CheckoutView update(Checkout checkout, CheckoutBaseReq req);

    public Checkout prepare(Checkout checkout, CheckoutBaseReq req);
}
