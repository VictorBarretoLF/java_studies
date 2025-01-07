package victor.sutdies.cleanarch.dataprovider.client.response;

import lombok.Data;

@Data
public class AddressResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private boolean erro;

    // you can add here a toDomain method to convert this response to a domain object

}