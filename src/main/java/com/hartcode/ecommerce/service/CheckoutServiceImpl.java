package com.hartcode.ecommerce.service;

import com.hartcode.ecommerce.dao.CustomerRepository;
import com.hartcode.ecommerce.dto.PaymentInfo;
import com.hartcode.ecommerce.dto.Purchase;
import com.hartcode.ecommerce.dto.PurchaseResponse;
import com.hartcode.ecommerce.entity.Customer;
import com.hartcode.ecommerce.entity.Order;
import com.hartcode.ecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret.test}") String secretKey) {
        this.customerRepository = customerRepository;

        // Initialise Stripe API with the secret key injected
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Retrieve the order from the dto
        Order order = purchase.getOrder();

        // Generate the tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // Populate the order with orderItem
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

        // Populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // Populate customer with order
        Customer customer = purchase.getCustomer();

        // Check if this is an existing customer
        Customer customerFromDb = customerRepository.findByEmail(customer.getEmail());

        // If customer exists in db, assign them
        if(customerFromDb != null) {
            customer = customerFromDb;
        }

        customer.add(order);

        // Save to the database
        customerRepository.save(customer);

        // Return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", paymentInfo.getAmount());
        parameters.put("currency", paymentInfo.getCurrency());
        parameters.put("payment_method_types", paymentMethodTypes);
        parameters.put("description", "UrbanHart Purchase");
        parameters.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(parameters);
    }

    private String generateOrderTrackingNumber() {

        // Generate a random UUID number
        return UUID.randomUUID().toString();
    }
}
