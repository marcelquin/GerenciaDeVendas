package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Model.Pedido;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClienteDTO {

    private Long id;

    private String nome;

    private String sobrenome;

    private Long cpf;

    private Date dataNascimento;

    private  String logradouro;

    private String numero;

    private String bairro;

    private Integer cep;

    private String cidade;

    private String estado;

    private Integer telefone;

    private Integer celular;

    private String email;

    private List<Pedido> pedidos;
}
