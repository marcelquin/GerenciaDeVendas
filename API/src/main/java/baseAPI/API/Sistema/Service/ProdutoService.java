package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.DTO.ProdutoDTO;
import baseAPI.API.Sistema.Model.Fornecedor;
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
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private FornecedorRepository frepository;

    public Produto listar()
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

    public Produto buscaPorId(Long id)
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

    public ProdutoDTO salvar(ProdutoDTO produtoDTO)
    {
        try{
            if(produtoDTO != null)
            {

               Produto produto = new Produto();
                BeanUtils.copyProperties(produtoDTO, produto);
                repository.save(produto);
                System.out.println("Salvo com sucesso");
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao salvar");
        }
        return null;
    }

    public void adicionarFornecedor(Long idproduto, Long idFornecedor)
    {
        if(idproduto != null && idFornecedor != null)
        {
            if(repository.existsById(idproduto))
            {
                Produto produto = repository.findById(idproduto).get();
                if(frepository.existsById(idFornecedor))
                {
                    Fornecedor fornecedor = frepository.findById(idFornecedor).get();
                    produto.setFornecedor(fornecedor);
                    repository.save(produto);
                }
            }
        }
    }

    public ProdutoDTO editar(Long id,ProdutoDTO produtoDTO)
    {
        try{
            if(repository.existsById(id))
            {
                if(produtoDTO != null)
                {

                    Produto produto = repository.findById(id).get();
                    BeanUtils.copyProperties(produtoDTO, produto);
                    repository.save(produto);
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
