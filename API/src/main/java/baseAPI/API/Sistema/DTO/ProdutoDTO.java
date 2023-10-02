package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Model.Fornecedor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Blob;

@Data
public class ProdutoDTO {

    private Long id;

    private String codigo;

    private Long fornecedor;

    private String nome;

    private String descrisao;

    private Double quantidade;

    private Double preco;

    @Lob
    private Blob imagem;

    private Long estoque;

}
