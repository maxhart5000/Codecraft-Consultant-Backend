/*
 * DTO class representing payment information.
 */
package com.hartcode.application.dto;

import lombok.Data;

@Data
public class PaymentInfo {

    private int amount; // Amount of the payment
    private String currency; // Currency of the payment
    private String receiptEmail; // Email address for receipt
}
