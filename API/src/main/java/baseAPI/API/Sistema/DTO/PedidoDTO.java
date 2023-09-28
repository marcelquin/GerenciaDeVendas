package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Enum.Status;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {

    private Long id;

    private List<Produto> produtos;

    private Cliente cliente;

    private Double valorTotal;

    private Status status;
}
