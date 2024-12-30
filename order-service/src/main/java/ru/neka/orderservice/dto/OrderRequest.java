package ru.neka.orderservice.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String userId;
    private String orderNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
