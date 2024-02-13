/*
 * Controller class responsible for managing checkout operations such as placing orders and creating payment intents.
 */
package com.hartcode.application.controller;

import com.hartcode.application.dto.PaymentInfo;
import com.hartcode.application.dto.Purchase;
import com.hartcode.application.dto.PurchaseResponse;
import com.hartcode.application.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/checkout")
public class CheckoutController {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private final CheckoutService checkoutService;

    /**
     * Constructor for CheckoutController.
     *
     * @param checkoutService Service responsible for checkout operations.
     */
    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /**
     * Endpoint for placing an order.
     *
     * @param purchase Purchase request containing order details.
     * @return Response entity containing the order tracking number.
     */
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        logger.info("purchase.billingAddress.city: " + purchase.getBillingAddress().getCity());
        logger.info("purchase.shippingAddress.city: " + purchase.getShippingAddress().getCity());

        // Call service method to place the order
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }

    /**
     * Endpoint for creating a payment intent.
     *
     * @param paymentInfo Payment information for creating the payment intent.
     * @return Response entity containing the payment intent details.
     * @throws StripeException If an error occurs during payment intent creation.
     */
    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
        // Log payment information
        logger.info("paymentInfo.amount: " + paymentInfo.getAmount() + ", paymentInfo.currency: " + paymentInfo.getCurrency());
        // Call service method to create payment intent
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
        String paymentString = paymentIntent.toJson();
        return new ResponseEntity<>(paymentString, HttpStatus.OK);
    }
}
