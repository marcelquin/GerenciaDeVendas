package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.Enum.Status;
import baseAPI.API.Sistema.Model.Pedido;
import baseAPI.API.Sistema.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "/api/pedido", description = "Manipula dados relacionados a tabela Pedido")
public class PedidoController {

    @Autowired
    private final PedidoService service;

    @Operation(summary = "Lista Pedidos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping()
    public Pedido listarPedidos()
    {
        return service.listar();
    }

    @Operation(summary = "Busca Registro por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping("/id")
    public Pedido buscarPedidossPorId(Long id)
    {
        return service.buscaPorId(id);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/efetuarCompra")
    public PedidoDTO Salvar(PedidoDTO pedidoDTO, @RequestParam Long idCar, @RequestParam Long idCli)
    {
        return service.salvar(idCar, idCli);
    }


    @Operation(summary = "Edita Registro da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PutMapping("/id")
    public PedidoDTO EditarPedido(@RequestParam Long idPed, @RequestParam Long idCar, @RequestParam Long idCli)
    {
        return service.editar(idPed, idCar, idCli);
    }

    @Operation(summary = "Edita Registro da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PutMapping("/alterarStatusPagamento")
    public void alterarStatusPagamento(@RequestParam Long id, Status status){ service.alterarStatusPagamento(id, status);}

    @Operation(summary = "Deleta Registro da tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @DeleteMapping("/id")
    public void deletarpedido(Long id)
    {
        service.deletar(id);
    }
}
