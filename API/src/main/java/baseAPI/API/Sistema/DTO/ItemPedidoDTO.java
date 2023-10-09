package baseAPI.API.Sistema.DTO;


import lombok.Data;

@Data
public class ItemPedidoDTO {

    private Long id;

    private Long produto;

    private Double quantidade;

    private Double valorItem;


}
