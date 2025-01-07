package victor.sutdies.cleanarch.entrypoint.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import victor.sutdies.cleanarch.core.usecase.InsertCustomerUseCase;
import victor.sutdies.cleanarch.entrypoint.controller.mapper.CustomerMapper;
import victor.sutdies.cleanarch.entrypoint.controller.request.CustomerRequest;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> insertNewCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        var customer = customerMapper.toCustomer(customerRequest);
        insertCustomerUseCase.insert(customer, customerRequest.getZipCode());
        return ResponseEntity.ok().build();
    }

}
