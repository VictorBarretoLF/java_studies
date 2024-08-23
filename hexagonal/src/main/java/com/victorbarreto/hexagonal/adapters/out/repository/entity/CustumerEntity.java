package com.victorbarreto.hexagonal.adapters.out.repository.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "customers")
public class CustumerEntity {

    private String id;
    private String name;
    private AddressEntity addressEntity;
    private String cpf;
    private Boolean isValidCpf;

}
