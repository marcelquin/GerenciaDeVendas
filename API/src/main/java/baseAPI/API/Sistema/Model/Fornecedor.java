package baseAPI.API.Sistema.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresa;

    @Column(name = "Razao_Social")
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

    @OneToMany
    private List<Produto> produtos;

}
