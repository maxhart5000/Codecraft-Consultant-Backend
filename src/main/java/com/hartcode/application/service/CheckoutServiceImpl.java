/*
 * Service class for handling checkout operations.
 */
package com.hartcode.application.service;

import com.hartcode.application.dao.CustomerRepository;
import com.hartcode.application.dto.PaymentInfo;
import com.hartcode.application.dto.Purchase;
import com.hartcode.application.dto.PurchaseResponse;
import com.hartcode.application.entity.Customer;
import com.hartcode.application.entity.Order;
import com.hartcode.application.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final String secretKey;

    /**
     * Constructor for CheckoutServiceImpl.
     *
     * @param customerRepository Repository for customer entities.
     * @param secretKey          Stripe secret key for payment processing.
     */
    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret.test}") String secretKey) {
        this.customerRepository = customerRepository;
        this.secretKey = secretKey;
        // Initialize Stripe API with the secret key injected
        Stripe.apiKey = secretKey;
    }

    /**
     * Places an order.
     *
     * @param purchase Purchase object containing order information.
     * @return Response containing the order tracking number.
     */
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Retrieve the order from the DTO
        Order order = purchase.getOrder();

        // Generate the tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // Populate the order with order items
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

        // Populate order with billing address and shipping address
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        order.setStatus("Completed");

        // Populate customer with order
        Customer customer = purchase.getCustomer();

        // Check if this is an existing customer
        Customer customerFromDb = customerRepository.findByEmail(customer.getEmail());

        // If customer exists in the database, assign them
        if (customerFromDb != null) {
            customer = customerFromDb;
        }

        customer.add(order);

        // Save to the database
        customerRepository.save(customer);

        // Return a response containing the order tracking number
        return new PurchaseResponse(orderTrackingNumber);
    }

    /**
     * Creates a payment intent for processing payments.
     *
     * @param paymentInfo Payment information.
     * @return Payment intent object.
     * @throws StripeException If an error occurs during payment processing.
     */
    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodTypes = Collections.singletonList("card");

        // Construct parameters for payment intent
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", paymentInfo.getAmount());
        parameters.put("currency", paymentInfo.getCurrency());
        parameters.put("payment_method_types", paymentMethodTypes);
        parameters.put("description", "CodeCraft Consulting Purchase");
        parameters.put("receipt_email", paymentInfo.getReceiptEmail());

        // Create and return payment intent
        return PaymentIntent.create(parameters);
    }

    /**
     * Generates a random order tracking number.
     *
     * @return Random order tracking number.
     */
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
