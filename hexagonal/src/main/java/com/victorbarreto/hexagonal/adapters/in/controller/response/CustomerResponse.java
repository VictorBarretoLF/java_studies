package com.victorbarreto.hexagonal.adapters.in.controller.response;

import lombok.Data;

@Data
public class CustomerResponse {

    private String id;
    private String name;
    private AddressResponse addressResponse;
    private String cpf;
    private Boolean isValidCpf;

}
