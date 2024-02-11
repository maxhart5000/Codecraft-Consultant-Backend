package com.hartcode.ecommerce.service;

import com.hartcode.ecommerce.dto.PaymentInfo;
import com.hartcode.ecommerce.dto.Purchase;
import com.hartcode.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
