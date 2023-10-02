package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Enum.Status;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoDTO {

    private Long id;

    private List<ProdutoDTO> produtos;

    private ClienteDTO cliente;

    private Double valorTotal;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPedido;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate datapagamento;

    private Status status;
}
