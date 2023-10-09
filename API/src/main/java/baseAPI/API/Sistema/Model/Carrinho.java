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
@Entity
@Builder
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<ItemPedido> itemPedidos;

    private Double valorTotal;

    public Double calValorTotal() {
        return this.valorTotal = itemPedidos.stream().mapToDouble(itemPedidos -> itemPedidos.getValorItem()).sum();
    }



}
