package baseAPI.API.Sistema.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Cliente implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private Long cpf;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

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
    private List<Pedido> pedidos;

}
