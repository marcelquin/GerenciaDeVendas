package baseAPI.API.Sistema.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Produto produto;

    private Double quantidade;

    private Double valorItem;


    public Double CalValorItem() {
        return getprecoProduto() * this.quantidade;
    }

    public Long pegaIdProduto() {
        return produto.getId();
    }

    public Double getprecoProduto()
    {
        return produto.getPreco();
    }

    public Double getestoqueProduto()
    {
        return produto.getEstoque();
    }

}
