package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class FornecedorDTO {

    private Long id;

    private String empresa;

    private String razaoSocial;

    private Long cnpj;

    private  String logradouro;

    private String numero;

    private String bairro;

    private Integer cep;

    private String cidade;

    private String estado;

    private Integer telefone;

    private Integer celular;

    private String email;

    private List<Long> produtos;
}
