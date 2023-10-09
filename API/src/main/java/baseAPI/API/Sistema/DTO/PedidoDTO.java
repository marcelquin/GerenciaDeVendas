package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Model.Produto;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoDTO {

    private Long id;

    private List<Long> produtos;

    private Long cliente;

    private Double valorTotal;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPedido;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate datapagamento;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCancelamento;


}
