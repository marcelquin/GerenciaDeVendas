package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ProdutoDTO;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Model.Produto;
import baseAPI.API.Sistema.Repository.FornecedorRepository;
import baseAPI.API.Sistema.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

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
               if(frepository.existsById(produtoDTO.getFornecedor()))
               {
                   Fornecedor fornecedor = frepository.findById(produtoDTO.getFornecedor()).get();
                   produto.setFornecedor(fornecedor);
               }
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


        public ResponseEntity<byte[]> verImagemPorId(long id) throws IOException, SQLException
        {
        Produto entidade = repository.findById(id).get();
        byte[] imageBytes = null;
        imageBytes = entidade.getImagem().getBytes(1, (int) entidade.getImagem().length());
        return ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        }


        public void AdicionarImagem(Long id, MultipartFile file)
        {
            try{
                if(repository.existsById(id))
                {
                    Produto produto = repository.findById(id).get();

                    if(!file.isEmpty()){
                        byte[] bytes = file.getBytes();
                        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                        produto.setImagem(blob);
                    }

                    repository.save(produto);
                }
            }catch (Exception e)
            {
                e.getMessage();
                System.out.println("erro ao editar");
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

    public void BaixaEstoque(Long idprod, Double quantidade)
    {
        try{
            if(repository.existsById(idprod))
            {
                Produto produto = repository.findById(idprod).get();
                produto.setEstoque(produto.getEstoque() - quantidade);
                repository.save(produto);
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao editar");
        }
    }

    public void deletar(Long id)
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
    }


}
