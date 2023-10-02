package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.DTO.ProdutoDTO;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Model.Pedido;
import baseAPI.API.Sistema.Model.Produto;
import baseAPI.API.Sistema.Repository.PedidoRepository;
import baseAPI.API.Sistema.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido listar()
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

    public Pedido buscaPorId(Long id)
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

    public ResponseEntity<PedidoDTO> salvar(PedidoDTO pedidoDTO)
    {
        try{
            if(pedidoDTO != null)
            {

                Pedido pedido = new Pedido();
                BeanUtils.copyProperties(pedidoDTO, pedido);
                repository.save(pedido);
                System.out.println("Salvo com sucesso");
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao salvar");
        }
        return null;
    }


    public ResponseEntity<PedidoDTO> editar(Long id, PedidoDTO pedidoDTO)
    {
        try{
            if(repository.existsById(id))
            {
                if(pedidoDTO != null)
                {

                    Pedido pedido = new Pedido();
                    BeanUtils.copyProperties(pedidoDTO, pedido);
                    repository.save(pedido);
                    System.out.println("Salvo com sucesso");
                }
            }
        }catch (Exception e)
        {
            e.getMessage();
            System.out.println("erro ao editar");
        }
        return (ResponseEntity<PedidoDTO>) ok();
    }

    public Pedido deletar(Long id)
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
