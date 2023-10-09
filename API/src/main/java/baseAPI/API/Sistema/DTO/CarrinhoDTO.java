package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Model.ItemPedido;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CarrinhoDTO {

    private Long id;

    private List<Long> itemPedidos;

    private Double valorTotal;



}