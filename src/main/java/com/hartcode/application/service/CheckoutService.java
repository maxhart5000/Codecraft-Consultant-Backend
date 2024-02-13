/*
 * Service interface for handling checkout operations.
 */
package com.hartcode.application.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.hartcode.application.dto.PaymentInfo;
import com.hartcode.application.dto.Purchase;
import com.hartcode.application.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
