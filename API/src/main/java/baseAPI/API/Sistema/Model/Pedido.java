package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Carrinho produtos;

    @OneToOne
    private Cliente cliente;

    @Column(name = "Valor_Total")
    private Double valorTotal;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_Pedido")
    private LocalDate dataPedido;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_Pagamento")
    private LocalDate datapagamento;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_Cancelamento")
    private LocalDate dataCancelamento;

    @Enumerated
    private Status status;

    public double CalValorTotal() {
        this.valorTotal = produtos.getValorTotal();
        return valorTotal;
    }

}

