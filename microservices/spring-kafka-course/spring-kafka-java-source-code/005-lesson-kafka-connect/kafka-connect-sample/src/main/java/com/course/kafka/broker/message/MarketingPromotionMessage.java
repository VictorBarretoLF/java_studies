package com.course.kafka.broker.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketingPromotionMessage {

    private int promotionId;
    private String promotionName;
    private int promotionDiscountRate;
    private String isActive;

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public int getPromotionDiscountRate() {
        return promotionDiscountRate;
    }

    public void setPromotionDiscountRate(int promotionDiscountRate) {
        this.promotionDiscountRate = promotionDiscountRate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "MarketingPromotionMessage [promotionId=" + promotionId + ", promotionName=" + promotionName
                + ", promotionDiscountRate=" + promotionDiscountRate + ", isActive=" + isActive + "]";
    }

}
