package com.victorbarreto.kafka.core.api;

import com.victorbarreto.kafka.core.entity.Commodity;
import com.victorbarreto.kafka.core.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

    @Autowired
    private CommodityService service;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commodity> generateAllCommodities() {
        return service.generateDummyCommodities();
    }

}
