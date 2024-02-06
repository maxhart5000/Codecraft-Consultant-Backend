package com.hartcode.ecommerce.service;

import com.hartcode.ecommerce.dto.Purchase;
import com.hartcode.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
