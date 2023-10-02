package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/cliente")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "/api/cliente", description = "Manipula dados relacionados a tabela cliente")
public class ClienteController {

    @Autowired
    private final ClienteService service;

    @Operation(summary = "Lista cursos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping()
    public Cliente listar()
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
    public Cliente buscaPorId(Long id)
    {
        return service.buscaPorId(id);
    }

    @Operation(summary = "Salva Novo registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping()
    public ClienteDTO novocliente(ClienteDTO clienteDTO)
    {
        return service.salvar(clienteDTO);
    }

    @Operation(summary = "adiciona pedido ao cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/adicionarpedido")
    public void adicionarpedido(@RequestParam Long idcliente, @RequestParam Long idPedido)
    {
        service.adicionarPedido(idcliente, idPedido);
    }

    @Operation(summary = "Edita Registro da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PutMapping("/id")
    public ClienteDTO editarcliente(@RequestParam Long id, ClienteDTO clienteDTO)
    {
        return service.editar(id, clienteDTO);
    }

    @Operation(summary = "Deleta Registro da tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @DeleteMapping("/id")
    public void deletar(Long id)
    {
        service.deletar(id);
    }
}
