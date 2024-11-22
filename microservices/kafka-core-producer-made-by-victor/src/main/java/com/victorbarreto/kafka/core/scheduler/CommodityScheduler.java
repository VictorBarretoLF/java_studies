package com.victorbarreto.kafka.core.scheduler;

import com.victorbarreto.kafka.core.entity.Commodity;
import com.victorbarreto.kafka.core.producer.CommodityProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

//@Component
public class CommodityScheduler {

    private static final Logger logger = LoggerFactory.getLogger(CommodityScheduler.class);
    private static final String COMMODITY_API_URL = "http://localhost:8080/api/commodity/v1/all";

    @Autowired
    private CommodityProducer producer;

    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 5000)
    public void fetchAndSendCommodities() {
        try {
            Commodity[] commodities = restTemplate.getForObject(COMMODITY_API_URL, Commodity[].class);
            if (commodities != null) {
                Arrays.stream(commodities).forEach(producer::sendMessage);
                logger.info("Fetched and sent {} commodities", commodities.length);
            } else {
                logger.warn("No commodities fetched from the API");
            }
        } catch (Exception e) {
            logger.error("Failed to fetch and send commodities", e);
        }
    }

}
