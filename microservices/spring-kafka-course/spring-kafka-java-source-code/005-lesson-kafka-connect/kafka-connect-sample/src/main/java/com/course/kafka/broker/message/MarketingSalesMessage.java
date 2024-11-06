package com.course.kafka.broker.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketingSalesMessage {

    private int salesId;
    private int salesAmount;
    private String salesCurrency;

    @JsonProperty("sales_date")
    private int salesEpochDays;

    private String customerEmail;

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(int salesAmount) {
        this.salesAmount = salesAmount;
    }

    public String getSalesCurrency() {
        return salesCurrency;
    }

    public void setSalesCurrency(String salesCurrency) {
        this.salesCurrency = salesCurrency;
    }

    public int getSalesEpochDays() {
        return salesEpochDays;
    }

    public void setSalesEpochDays(int salesEpochDays) {
        this.salesEpochDays = salesEpochDays;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "MarketingSalesMessage [salesId=" + salesId + ", salesAmount=" + salesAmount + ", salesCurrency="
                + salesCurrency + ", salesEpochDays=" + salesEpochDays + ", customerEmail=" + customerEmail + "]";
    }

}
