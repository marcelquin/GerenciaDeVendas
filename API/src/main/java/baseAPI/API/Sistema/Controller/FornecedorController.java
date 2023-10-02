package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Service.ClienteService;
import baseAPI.API.Sistema.Service.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fornecedor")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "/api/fornecedor", description = "Manipula dados relacionados a tabela Fornecedor")
public class FornecedorController {

    @Autowired
    private final FornecedorService service;

    @Operation(summary = "Lista cursos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping()
    public Fornecedor listarfornecedores()
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
    public Fornecedor buscafornecedorPorId(Long id)
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
    public FornecedorDTO novofornecedor(FornecedorDTO fornecedorDTO)
    {
        return service.salvar(fornecedorDTO);
    }

    @Operation(summary = "Adiciona produtos relacionados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/adicionarproduto")
    public void adicionarProduto(@RequestParam Long idfornecedor, @RequestParam Long idProduto)
    {
        service.adicionarProduto(idfornecedor, idProduto);
    }

    @Operation(summary = "Edita Registro da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PutMapping("/id")
    public FornecedorDTO editarfornecedor(@RequestParam Long id, FornecedorDTO fornecedorDTO)
    {
        return service.editar(id, fornecedorDTO);
    }

    @Operation(summary = "Deleta Registro da tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @DeleteMapping("/id")
    public void deletarfornecedor(Long id)
    {
        service.deletar(id);
    }
}
