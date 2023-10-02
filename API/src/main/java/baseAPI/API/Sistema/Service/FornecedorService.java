package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Model.Pedido;
import baseAPI.API.Sistema.Model.Produto;
import baseAPI.API.Sistema.Repository.FornecedorRepository;
import baseAPI.API.Sistema.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private ProdutoRepository prepository;

    public Fornecedor listar()
    {
        try{
            repository.findAll();
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao listar");
        }
        return null;
    }

    public Fornecedor buscaPorId(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                repository.findById(id);
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao buscar");
        }
        return null;
    }

    public FornecedorDTO salvar(FornecedorDTO fornecedorDTO)
    {
        try{
            if(fornecedorDTO != null)
            {
               Fornecedor fornecedor = new Fornecedor();
                BeanUtils.copyProperties(fornecedorDTO, fornecedor);
                repository.save(fornecedor);
                System.out.println("Salvo com sucesso");
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao salvar");
        }
        return null;
    }

    public void adicionarProduto(Long idfornecedor, Long idProduto)
    {
        if(idfornecedor != null && idProduto != null)
        {
            if(repository.existsById(idfornecedor))
            {
                Fornecedor fornecedor = repository.findById(idfornecedor).get();
                if(prepository.existsById(idProduto))
                {
                    Produto produto = prepository.findById(idProduto).get();
                    List<Produto> produtos = new ArrayList<>();
                    produtos.add(produto);
                    fornecedor.setProdutos(produtos);
                    fornecedor.setId(idfornecedor);
                    repository.save(fornecedor);
                }
            }
        }
    }

    public FornecedorDTO editar(Long id,FornecedorDTO fornecedorDTO)
    {
        try{
            if(repository.existsById(id))
            {
                if(fornecedorDTO != null)
                {

                    Fornecedor fornecedor = new Fornecedor();
                    BeanUtils.copyProperties(fornecedorDTO, fornecedor);
                    fornecedor.setId(id);
                    repository.save(fornecedor);
                    System.out.println("Salvo com sucesso");
                }
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao editar");
        }
        return null;
    }

    public Fornecedor deletar(Long id)
    {
        try{
            if(repository.existsById(id))
            {
                repository.deleteById(id);
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao deletar");
        }
        return null;
    }
}
