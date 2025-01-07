package victor.sutdies.cleanarch.entrypoint.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import victor.sutdies.cleanarch.entrypoint.validator.ValidCEP;

@Data
public class CustomerRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @ValidCEP
    @NotBlank
    private String zipCode;

}