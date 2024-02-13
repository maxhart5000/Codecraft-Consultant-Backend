/*
 * DTO class representing a response to a purchase request.
 */
package com.hartcode.application.dto;

import lombok.Data;

@Data
public class PurchaseResponse {

    private final String orderTrackingNumber; // Tracking number for the order
}
