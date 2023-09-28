package baseAPI.API.Sistema.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @ManyToOne(targetEntity = Fornecedor.class, cascade = CascadeType.ALL)
    private Fornecedor fornecedor;

    private String nome;

    private String descrisao;

    private Double quantidade;

    private Double preco;

    @Lob
    private Blob imagem;

    private Long estoque;



}
