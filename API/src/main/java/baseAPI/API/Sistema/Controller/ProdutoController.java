package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.DTO.ProdutoDTO;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Model.Produto;
import baseAPI.API.Sistema.Service.FornecedorService;
import baseAPI.API.Sistema.Service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produto")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "/api/produto", description = "Manipula dados relacionados a tabela Fornecedor")
public class ProdutoController {

    @Autowired
    private final ProdutoService service;

    @Operation(summary = "Lista cursos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping()
    public Produto listarprodutos()
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
    public Produto buscarprodutoPorId(Long id)
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
    public ProdutoDTO novoproduto(ProdutoDTO produtoDTO)
    {
        return service.salvar(produtoDTO);
    }

    @Operation(summary = "Adiciona produtos relacionados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/adicionarfornecedor")
    public void adicionarFornecedor(@RequestParam Long idproduto, @RequestParam Long idFornecedor)
    {
        service.adicionarFornecedor(idproduto, idFornecedor);
    }

    @Operation(summary = "Edita Registro da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PutMapping("/id")
    public ProdutoDTO editarproduto(@RequestParam Long id, ProdutoDTO produtoDTO)
    {
        return service.editar(id, produtoDTO);
    }

    @Operation(summary = "Deleta Registro da tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @DeleteMapping("/id")
    public void deletarproduto(Long id)
    {
        service.deletar(id);
    }
}
