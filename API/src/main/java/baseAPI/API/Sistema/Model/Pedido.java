package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Produto> produtos;

    @OneToOne
    private Cliente cliente;

    @Column(name = "Valor_Total")
    private Double valorTotal;

    private Status status;
}
